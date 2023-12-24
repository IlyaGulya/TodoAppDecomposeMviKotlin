package example.todo.common.database

import app.cash.sqldelight.Query
import app.cash.sqldelight.async.coroutines.awaitAsList
import app.cash.sqldelight.db.SqlDriver
import com.badoo.reaktive.base.setCancellable
import com.badoo.reaktive.completable.Completable
import com.badoo.reaktive.coroutinesinterop.completableFromCoroutine
import com.badoo.reaktive.coroutinesinterop.maybeFromCoroutine
import com.badoo.reaktive.coroutinesinterop.singleFromCoroutine
import com.badoo.reaktive.maybe.Maybe
import com.badoo.reaktive.maybe.mapNotNull
import com.badoo.reaktive.observable.Observable
import com.badoo.reaktive.observable.autoConnect
import com.badoo.reaktive.observable.firstOrError
import com.badoo.reaktive.observable.flatMapSingle
import com.badoo.reaktive.observable.observable
import com.badoo.reaktive.observable.observeOn
import com.badoo.reaktive.observable.replay
import com.badoo.reaktive.scheduler.ioScheduler
import com.badoo.reaktive.single.Single
import com.badoo.reaktive.single.asObservable
import com.badoo.reaktive.single.flatMapCompletable
import com.badoo.reaktive.single.flatMapMaybe
import com.badoo.reaktive.single.flatMapObservable
import com.badoo.reaktive.single.map
import com.badoo.reaktive.single.observeOn
import com.badoo.reaktive.single.singleOf
import example.todo.database.TodoDatabase

class DefaultTodoSharedDatabase(driver: Single<SqlDriver>) : TodoSharedDatabase {

    constructor(driver: SqlDriver) : this(singleOf(driver))

    private val queries: Single<TodoDatabaseQueries> =
        driver
            .map { it ->
                TodoDatabase(
                    it, TodoItemEntity.Adapter(
                        isDoneAdapter = WrappedBooleanAdapter()
                    )
                ).todoDatabaseQueries
            }
            .asObservable()
            .replay()
            .autoConnect()
            .firstOrError()

    override fun observeAll(): Observable<List<TodoItemEntity>> =
        query(TodoDatabaseQueries::selectAll)
            .observe { it.awaitAsList() }

    override fun select(id: Long): Maybe<TodoItemEntity> =
        query { it.select(id = id) }
            .flatMapMaybe { maybeFromCoroutine { it.awaitAsList() } }
            .mapNotNull { it.firstOrNull() }

    override fun add(text: String): Completable =
        execute { it.add(text = text) }

    override fun setText(id: Long, text: String): Completable =
        execute { it.setText(id = id, text = text) }

    override fun setDone(id: Long, isDone: Boolean): Completable =
        execute { it.setDone(id = id, isDone = isDone.wrap()) }

    override fun delete(id: Long): Completable =
        execute { it.delete(id = id) }

    override fun clear(): Completable =
        execute { it.clear() }

    private fun <T : Any> query(query: (TodoDatabaseQueries) -> Query<T>): Single<Query<T>> =
        queries
            .observeOn(ioScheduler)
            .map(query)

    private fun execute(query: suspend (TodoDatabaseQueries) -> Unit): Completable =
        queries
            .observeOn(ioScheduler)
            .flatMapCompletable { completableFromCoroutine { query(it) } }

    private fun <T : Any, R> Single<Query<T>>.observe(get: suspend (Query<T>) -> R): Observable<R> =
        flatMapObservable { it.observed() }
            .observeOn(ioScheduler)
            .flatMapSingle { singleFromCoroutine { get(it) } }

    private fun <T : Any> Query<T>.observed(): Observable<Query<T>> =
        observable { emitter ->
            val listener =
                object : Query.Listener {
                    override fun queryResultsChanged() {
                        emitter.onNext(this@observed)
                    }
                }

            emitter.onNext(this@observed)
            addListener(listener)
            emitter.setCancellable { removeListener(listener) }
        }
}
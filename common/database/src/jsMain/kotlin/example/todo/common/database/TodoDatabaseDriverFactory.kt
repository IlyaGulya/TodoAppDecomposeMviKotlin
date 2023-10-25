package example.todo.common.database

import app.cash.sqldelight.async.coroutines.awaitCreate
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.worker.WebWorkerDriver
import com.badoo.reaktive.coroutinesinterop.singleFromCoroutine
import com.badoo.reaktive.single.Single
import example.todo.database.TodoDatabase
import org.w3c.dom.Worker

fun todoDatabaseDriver(): Single<SqlDriver> =
    singleFromCoroutine {
        val worker = Worker(js("""new URL("sqlite.worker.js", import.meta.url)""").unsafeCast<String>())
        val driver: SqlDriver = WebWorkerDriver(worker)

//        val driver = WebWorkerDriver(
//            Worker(
//                js("""new URL("@cashapp/sqldelight-sqljs-worker/sqljs.worker.js", import.meta.url)""")
//            )
//        )
        TodoDatabase.Schema.awaitCreate(driver)
        driver
    }

package example.todo.common.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.badoo.reaktive.coroutinesinterop.singleFromCoroutine
import com.badoo.reaktive.single.Single
import example.todo.database.TodoDatabase
import java.io.File

@Suppress("FunctionName") // FactoryFunction
fun TodoDatabaseDriver(): Single<SqlDriver> = singleFromCoroutine {
    val databasePath = File(System.getProperty("java.io.tmpdir"), "ComposeTodoDatabase.db")

    JdbcSqliteDriver(url = "jdbc:sqlite:${databasePath.absolutePath}").also { driver ->
        TodoDatabase.Schema.create(driver).await()
    }
}

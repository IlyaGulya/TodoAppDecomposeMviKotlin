package example.todo.common.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import example.todo.database.TodoDatabase
import java.io.File

@Suppress("FunctionName") // FactoryFunction
fun TodoDatabaseDriver(): SqlDriver {
    val databasePath = File(System.getProperty("java.io.tmpdir"), "ComposeTodoDatabase.db")
    val driver = JdbcSqliteDriver(url = "jdbc:sqlite:${databasePath.absolutePath}")
    TodoDatabase.Schema.create(driver)

    return driver
}

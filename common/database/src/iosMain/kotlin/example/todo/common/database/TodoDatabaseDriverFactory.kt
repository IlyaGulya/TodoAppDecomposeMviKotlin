package example.todo.common.database

import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import example.todo.database.TodoDatabase

@Suppress("FunctionName") // Factory function
fun TodoDatabaseDriver(): SqlDriver =
    NativeSqliteDriver(TodoDatabase.Schema.synchronous(), "TodoDatabase.db")

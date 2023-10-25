package example.todo.common.database

import android.content.Context
import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import example.todo.database.TodoDatabase

@Suppress("FunctionName") // FactoryFunction
fun TodoDatabaseDriver(context: Context): SqlDriver =
    AndroidSqliteDriver(
        schema = TodoDatabase.Schema.synchronous(),
        context = context,
        name = "TodoDatabase.db"
    )

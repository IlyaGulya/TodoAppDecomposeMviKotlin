package example.todo.common.database

import app.cash.sqldelight.ColumnAdapter

// TODO: Remove when fixed: https://github.com/cashapp/sqldelight/issues/4750
class WrappedBooleanAdapter :
    ColumnAdapter<WrappedBoolean, Long> {
    override fun decode(databaseValue: Long): WrappedBoolean {
        return WrappedBoolean(databaseValue == 1L)
    }

    override fun encode(value: WrappedBoolean): Long {
        return if (value.value) 1L else 0L
    }

}
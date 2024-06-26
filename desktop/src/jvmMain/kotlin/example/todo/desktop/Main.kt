package example.todo.desktop

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.extensions.compose.lifecycle.LifecycleController
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.badoo.reaktive.coroutinesinterop.asScheduler
import com.badoo.reaktive.scheduler.overrideSchedulers
import example.todo.common.database.DefaultTodoSharedDatabase
import example.todo.common.database.TodoDatabaseDriver
import example.todo.common.root.TodoRoot
import example.todo.common.root.integration.TodoRootComponent
import example.todo.common.ui.TodoRootContent
import kotlinx.coroutines.Dispatchers

fun main() {
    overrideSchedulers(main = Dispatchers.Main::asScheduler)

    val lifecycle = LifecycleRegistry()
    application {
        val root = remember { todoRoot(DefaultComponentContext(lifecycle = lifecycle)) }

        val windowState = rememberWindowState()
        LifecycleController(lifecycle, windowState)

        Window(
            onCloseRequest = ::exitApplication,
            state = windowState,
            title = "Todo"
        ) {
            Surface(modifier = Modifier.fillMaxSize()) {
                MaterialTheme {
                    TodoRootContent(root)
                }
            }
        }
    }
}

private fun todoRoot(componentContext: ComponentContext): TodoRoot =
    TodoRootComponent(
        componentContext = componentContext,
        storeFactory = DefaultStoreFactory(),
        database = DefaultTodoSharedDatabase(TodoDatabaseDriver())
    )

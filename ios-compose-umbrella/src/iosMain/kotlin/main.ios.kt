import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.ComposeUIViewController
import example.todo.common.root.TodoRoot
import example.todo.common.ui.TodoRootContent
import platform.UIKit.UIViewController

fun MainViewController(root: TodoRoot): UIViewController {
    return ComposeUIViewController {
        Surface(modifier = Modifier.fillMaxSize()) {
            MaterialTheme {
                TodoRootContent(root)
            }
        }
    }
}
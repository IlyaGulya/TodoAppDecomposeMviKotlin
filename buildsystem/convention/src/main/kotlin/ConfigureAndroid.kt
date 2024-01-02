import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.the

fun Project.configureAndroid(init: LibraryExtension.() -> Unit = {}) {
    this.pluginManager.apply("com.android.library")
    the<com.android.build.gradle.LibraryExtension>().run {
        compileSdk = 34

        defaultConfig {
            minSdk = 23
        }

        init()
    }
}
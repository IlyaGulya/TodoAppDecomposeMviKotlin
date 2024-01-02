import org.gradle.api.Project
import org.gradle.kotlin.dsl.the
import org.jetbrains.compose.ComposeExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

fun Project.configureMultiplatformCompose() {
    configureMultiplatform()

    pluginManager.apply("org.jetbrains.compose")
    pluginManager.withPlugin("org.jetbrains.compose") {
        configureCompose()
    }
}

internal fun Project.configureCompose() {
    val compose = the<ComposeExtension>()
    the<KotlinMultiplatformExtension>().apply {
        sourceSets.apply {
            commonMain {
                dependencies {
                    implementation(compose.dependencies.runtime)
                    implementation(compose.dependencies.foundation)
                    implementation(compose.dependencies.material)
                }
            }

            androidMain.dependencies {
                implementation(libs.androidx.core.ktx)
            }

            getByName("desktopMain").dependencies {
                implementation(compose.dependencies.desktop.common)
            }
        }
    }

}
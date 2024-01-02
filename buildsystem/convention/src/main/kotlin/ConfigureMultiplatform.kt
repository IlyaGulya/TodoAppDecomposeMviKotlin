import org.gradle.api.Project
import org.gradle.kotlin.dsl.getValue
import org.gradle.kotlin.dsl.getting
import org.gradle.kotlin.dsl.the
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

fun Project.configureMultiplatform() {
    configureAndroid()
    pluginManager.apply("org.jetbrains.kotlin.multiplatform")
    the<KotlinMultiplatformExtension>().apply {
        applyDefaultHierarchyTemplate()
        jvmToolchain(17)
        jvm("desktop")
        androidTarget()
        iosArm64()
        iosSimulatorArm64()
        js {
            browser()
        }

        sourceSets.apply {
            commonTest.dependencies {
                implementation(libs.kotlin.test.common)
                implementation(libs.kotlin.test.annotations.common)
            }

            val androidUnitTest by getting {
                dependencies {
                    implementation(libs.kotlin.test.junit)
                }
            }

            val desktopTest by getting {
                dependencies {
                    implementation(libs.kotlin.test.junit)
                }
            }

            jsTest.dependencies {
                implementation(libs.kotlin.test.js)
            }
        }
    }
}
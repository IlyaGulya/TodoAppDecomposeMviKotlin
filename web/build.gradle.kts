import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

kotlin {
    js {
        browser {
            useCommonJs()
        }
        binaries.executable()
    }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        moduleName = "composeApp"
        browser {
            commonWebpackConfig {
                outputFileName = "composeApp.js"
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    static = (static ?: mutableListOf()).apply {
                        // Serve sources to debug inside browser
                        add(project.projectDir.path)
                        add(project.projectDir.path + "/commonMain/")
                        add(project.projectDir.path + "/wasmJsMain/")
                    }
                }
            }
        }
        binaries.executable()
    }

    sourceSets {
        jsMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.html.core)
            implementation(project(":common:utils"))
            implementation(project(":common:database"))
            implementation(project(":common:root"))
            implementation(project(":common:main"))
            implementation(project(":common:edit"))
            implementation(libs.arkivanov.decompose)
            implementation(libs.arkivanov.mvikotlin)
            implementation(libs.arkivanov.mvikotlin.main)
            implementation(libs.badoo.reaktive)
            implementation(libs.squareup.sqldelight.webworker.driver)
            implementation(npm("@sqlite.org/sqlite-wasm", "3.43.2-build1"))
            implementation(npm("copy-webpack-plugin", "11.0.0"))
            implementation(npm("@material-ui/icons", "4.11.2"))
        }
    }
}

compose.experimental {
    web.application {}
}
plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

kotlin {
    js(IR) {
        browser {
            useCommonJs()
            binaries.executable()
        }
    }

    sourceSets {
        named("jsMain") {
            dependencies {
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
                implementation(npm("@cashapp/sqldelight-sqljs-worker", "2.0.0"))
                implementation(npm("sql.js", "1.8.0"))
                implementation(npm("copy-webpack-plugin", "11.0.0"))
                implementation(npm("@material-ui/icons", "4.11.2"))
            }
        }
    }
}
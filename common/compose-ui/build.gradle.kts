plugins {
    id("multiplatform-compose-setup")
    id("build-tools")
}

configureAndroid()


android.namespace = "example.todo.common.ui"
kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":common:main"))
                implementation(project(":common:edit"))
                implementation(project(":common:root"))
                implementation(libs.arkivanov.decompose)
                implementation(libs.arkivanov.decompose.extensions.compose)
            }
        }
    }
}

plugins {
    id("build-tools")
}

configureMultiplatform()

android.namespace = "example.todo.common.list"

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.arkivanov.mvikotlin)
                implementation(libs.arkivanov.decompose)
                implementation(libs.badoo.reaktive)
            }
        }
    }
}

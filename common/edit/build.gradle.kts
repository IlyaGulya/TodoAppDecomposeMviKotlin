plugins {
    id("multiplatform-setup")
    id("android-setup")
}

android.namespace = "example.todo.common.edit"

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":common:utils"))
                implementation(project(":common:database"))
                implementation(libs.arkivanov.mvikotlin)
                implementation(libs.arkivanov.mvikotlin.extensions.reaktive)
                implementation(libs.arkivanov.decompose)
                implementation(libs.badoo.reaktive)
            }
        }
    }
}

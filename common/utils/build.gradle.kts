plugins {
    id("multiplatform-setup")
    id("android-setup")
}

android.namespace = "example.todo.common.list"

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.arkivanov.mvikotlin.rx)
                implementation(libs.arkivanov.mvikotlin)
                implementation(libs.arkivanov.decompose)
                implementation(libs.badoo.reaktive)
            }
        }
    }
}

plugins {
    id("multiplatform-setup")
    id("android-setup")
}

android.namespace = "example.todo.common.main"

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":common:utils"))
                implementation(project(":common:database"))
                implementation(libs.arkivanov.decompose)
                implementation(libs.arkivanov.mvikotlin)
                implementation(libs.arkivanov.mvikotlin.extensions.reaktive)
                implementation(libs.badoo.reaktive)
            }
        }

        commonTest {
            dependencies {
                implementation(libs.arkivanov.mvikotlin.main)
                implementation(libs.badoo.reaktive.testing)
            }
        }
    }
}

plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.kotlin.compose)
  alias(libs.plugins.compose)
}

android {
    compileSdk = 35
    namespace = "example.todo.android"

    defaultConfig {
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    packaging {
        resources {
            excludes += "META-INF/*"
        }
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":common:database"))
    implementation(project(":common:utils"))
    implementation(project(":common:root"))
    implementation(project(":common:compose-ui"))
    implementation(compose.material)
    implementation(libs.arkivanov.mvikotlin)
    implementation(libs.arkivanov.mvikotlin.main)
    implementation(libs.arkivanov.mvikotlin.logging)
    implementation(libs.arkivanov.mvikotlin.timetravel)
    implementation(libs.arkivanov.decompose)
    implementation(libs.arkivanov.decompose.extensions.compose)
    implementation(libs.androidx.activity.compose)
}

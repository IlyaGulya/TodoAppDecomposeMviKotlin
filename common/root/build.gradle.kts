import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.konan.target.Family

plugins {
    id("multiplatform-setup")
    id("android-setup")
    id("kotlin-parcelize")
}

android.namespace = "example.todo.common.root"

kotlin {
    targets
        .filterIsInstance<KotlinNativeTarget>()
        .filter { it.konanTarget.family == Family.IOS }
        .forEach { target ->
            target.binaries {
                framework {
                    baseName = "Todo"
                    linkerOpts.add("-lsqlite3")
                    export(project(":common:database"))
                    export(project(":common:main"))
                    export(project(":common:edit"))
                    export(libs.arkivanov.mvikotlin)
                    export(libs.arkivanov.mvikotlin.main)
                    export(libs.arkivanov.decompose)
                    export(libs.arkivanov.essenty.lifecycle)
                }
            }
        }

    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":common:utils"))
                implementation(project(":common:database"))
                implementation(project(":common:main"))
                implementation(project(":common:edit"))
                api(libs.arkivanov.mvikotlin)
                api(libs.arkivanov.mvikotlin.main)
                api(libs.arkivanov.decompose)
                api(libs.arkivanov.essenty.lifecycle)
                implementation(libs.badoo.reaktive)
            }
        }
    }

    sourceSets {
        val iosMain by getting {
            dependencies {
                api(project(":common:database"))
                api(project(":common:main"))
                api(project(":common:edit"))
                api(libs.arkivanov.decompose)
                api(libs.arkivanov.mvikotlin.main)
                api(libs.arkivanov.essenty.lifecycle)
            }
        }
    }
}

import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.konan.target.Family

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

kotlin {
    applyDefaultHierarchyTemplate()
    iosArm64()
    iosSimulatorArm64()

    targets
        .filterIsInstance<KotlinNativeTarget>()
        .filter { it.konanTarget.family == Family.IOS }
        .forEach { iosTarget ->
            iosTarget.binaries.framework {
                baseName = "Todo"
                isStatic = true
                linkerOpts.add("-lsqlite3")
                export(project(":common:database"))
                export(project(":common:root"))
                export(libs.arkivanov.mvikotlin)
                export(libs.arkivanov.mvikotlin.main)
                export(libs.arkivanov.decompose)
                export(libs.arkivanov.essenty.lifecycle)

            }
        }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                api(project(":common:root"))
                api(project(":common:database"))
                implementation(project(":common:compose-ui"))
            }
        }
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "11"
    }
}

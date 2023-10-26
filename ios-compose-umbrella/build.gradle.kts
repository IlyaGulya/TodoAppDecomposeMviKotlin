plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

kotlin {
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
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

        val iosMain by creating {
            dependsOn(commonMain)
        }
        val iosX64Main by getting {
            dependsOn(iosMain)
        }
        val iosArm64Main by getting {
            dependsOn(iosMain)
        }
        val iosSimulatorArm64Main by getting {
            dependsOn(iosMain)
        }
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "11"
    }
}

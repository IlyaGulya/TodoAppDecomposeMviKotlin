import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    // kotlin("jvm") doesn't work well in IDEA/AndroidStudio (https://github.com/JetBrains/compose-jb/issues/22)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.compose)
}

kotlin {
    jvm {
        withJava()
    }

    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation(project(":common:utils"))
                implementation(project(":common:database"))
                implementation(project(":common:root"))
                implementation(project(":common:compose-ui"))
                implementation(libs.coroutines.swing)
                implementation(libs.arkivanov.decompose)
                implementation(libs.arkivanov.decompose.extensions.compose)
                implementation(libs.arkivanov.mvikotlin)
                implementation(libs.arkivanov.mvikotlin.main)
                implementation(libs.badoo.reaktive)
                implementation(libs.badoo.reaktive.coroutines.interop)
            }
        }
    }
}

compose.desktop {
    application {
        mainClass = "example.todo.desktop.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "ComposeDesktopTodo"
            packageVersion = "1.0.0"

            modules("java.sql")

            windows {
                menuGroup = "Compose Examples"
                // see https://wixtoolset.org/documentation/manual/v3/howtos/general/generate_guids.html
                upgradeUuid = "BF9CDA6A-1391-46D5-9ED5-383D6E68CCEB"
            }
        }
    }
}

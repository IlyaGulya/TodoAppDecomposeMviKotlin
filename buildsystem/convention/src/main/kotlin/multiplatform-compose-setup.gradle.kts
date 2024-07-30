plugins {
    id("multiplatform-setup")
    id("org.jetbrains.kotlin.plugin.compose")
    id("org.jetbrains.compose")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(libs.androidx.core.ktx)
            }
        }

        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.common)
            }
        }
    }
}
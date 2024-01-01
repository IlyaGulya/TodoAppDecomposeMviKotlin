plugins {
    id("com.android.library")
    kotlin("multiplatform")
}

kotlin {
    jvm("desktop")
    androidTarget()
    iosArm64()
    iosSimulatorArm64()
    js {
        browser()
    }

    sourceSets {
        commonTest.dependencies {
            implementation(libs.kotlin.test.common)
            implementation(libs.kotlin.test.annotations.common)
        }

        val androidUnitTest by getting {
            dependencies {
                implementation(libs.kotlin.test.junit)
            }
        }

        val desktopTest by getting {
            dependencies {
                implementation(libs.kotlin.test.junit)
            }
        }

        jsTest.dependencies {
            implementation(libs.kotlin.test.js)
        }
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "11"
    }
}

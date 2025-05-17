plugins {
    id("com.android.library")
    kotlin("multiplatform")
}

kotlin {
    jvmToolchain(17)
    jvm("desktop")
    androidTarget()
    iosArm64()
    iosSimulatorArm64()
    js {
        browser()
    }

    compilerOptions {

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
}

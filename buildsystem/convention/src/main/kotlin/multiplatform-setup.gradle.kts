plugins {
    id("com.android.library")
    id("kotlin-multiplatform")
}

kotlin {
    jvm("desktop")
    androidTarget()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    js(IR) {
        browser()
    }

    sourceSets {
        create("iosMain") {
            dependsOn(getByName("commonMain"))
        }
        create("iosTest") {
            dependsOn(getByName("commonTest"))
        }

        getByName("iosX64Main") {
            dependsOn(getByName("iosMain"))
        }
        getByName("iosX64Test") {
            dependsOn(getByName("iosTest"))
        }

        getByName("iosArm64Main") {
            dependsOn(getByName("iosMain"))
        }
        getByName("iosArm64Test") {
            dependsOn(getByName("iosTest"))
        }

        getByName("iosSimulatorArm64Main") {
            dependsOn(getByName("iosMain"))
        }
        getByName("iosSimulatorArm64Test") {
            dependsOn(getByName("iosTest"))
        }

        named("commonTest") {
            dependencies {
                implementation(libs.kotlin.test.common)
                implementation(libs.kotlin.test.annotations.common)
            }
        }

        named("androidUnitTest") {
            dependencies {
                implementation(libs.kotlin.test.junit)
            }
        }
        named("desktopTest") {
            dependencies {
                implementation(libs.kotlin.test.junit)
            }
        }
        named("jsTest") {
            dependencies {
                implementation(libs.kotlin.test.js)
            }
        }
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "11"
    }
}

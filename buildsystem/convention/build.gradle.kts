plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(libs.compose.gradle.plugin)
    implementation(libs.kotlin.gradle.plugin)
    implementation(libs.kotlin.compose.gradle.plugin)
    implementation(libs.android.tools.build.gradle.plugin)
    implementation(libs.squareup.sqldelight.gradle.plugin)
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}
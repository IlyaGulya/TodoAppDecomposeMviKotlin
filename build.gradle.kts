plugins {
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.compose) apply false
    alias(libs.plugins.squareup.sqldelight) apply false
}

//allprojects {
//    afterEvaluate {
//        // Workaround for https://youtrack.jetbrains.com/issue/KT-52776
//        rootProject.extensions.findByType<org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension>()
//            ?.apply {
//                versions.webpackCli.version = "4.10.0"
//            }
//    }
//}

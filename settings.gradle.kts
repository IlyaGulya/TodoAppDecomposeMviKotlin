pluginManagement {
    repositories {
        repositories {
            mavenLocal()
            gradlePluginPortal()
            mavenCentral()
            google()
            maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        }
    }
    includeBuild("buildsystem/convention")
}

dependencyResolutionManagement {
// Not supported by :web because of nodejs repositories being injected by kotlin-multiplatform plugin
//    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)

    repositories {
        mavenLocal()
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}


include(
    ":common:utils",
    ":common:database",
    ":common:main",
    ":common:edit",
    ":common:root",
    ":common:compose-ui",
    ":android",
    ":desktop",
    ":web"
)

An example of Kotlin Multiplatform todo app with shared Android/Desktop Compose UI and SwiftUI (not
Compose) iOS.

Previously this code was located in [JetBrains/compose-multiplatform] repository.

But it was deleted
in [this commit](https://github.com/JetBrains/compose-multiplatform/commit/563575a866c09c283ba9998e3ab44888e8a00779)

I've copied it to a separate repo and maintaining it here now.

This example supports the following targets:

- `Android` (Compose)
- `Desktop/JVM` (Compose)
- `Web/JavaScript` (Compose)
- `iOS` (Both SwiftUI and Compose)

Libraries used:

- Compose Multiplatform - shared UI
- [Decompose](https://github.com/arkivanov/Decompose) - navigation and lifecycle
- [MVIKotlin](https://github.com/arkivanov/MVIKotlin) - presentation and business logic
- [Reaktive](https://github.com/badoo/Reaktive) - background processing and data transformation
- [SQLDelight](https://github.com/cashapp/sqldelight) - data storage

There are multiple modules:

- `:common:utils` - just some useful helpers
- `:common:database` - SQLDelight database definition
- `:common:main` - displays a list of todo items and a text field
- `:common:edit` - accepts an item id and allows editing
- `:common:root` - navigates between `main` and `edit` screens
- `:common:compose-ui` - Shared Compose UI for Android and Desktop
- `:android` - Android application
- `:desktop` - Desktop application
- `:web` - Web browser application + Compose HTML Library
- `:ios-compose-umbrella` - iOS Umbrella module with UI in Compose Multiplatform. Used in
  ios-compose application
- `ios` - iOS SwiftUI Xcode project
- `ios-compose` - iOS Compose Multiplatform Xcode project

The root module is integrated into Android, Desktop and iOS (non-Compose) apps.

Features:

- 99% of the code is shared: data, business logic, presentation, navigation and UI
- View state is preserved when navigating between screens, Android configuration change, etc.
- Model-View-Intent (aka MVI) architectural pattern
- Pluggable UI - Compose UI for Android, Desktop and Web, SwiftUI (not Compose) for iOS

### Running desktop application

* To run, launch command: `./gradlew :desktop:run`
* Or choose **desktop** configuration in IDE and run it.  
  ![desktop-run-configuration.png](screenshots/desktop-run-configuration.png)

#### Building native desktop distribution

```
./gradlew :desktop:packageDistributionForCurrentOS
# outputs are written to desktop/build/compose/binaries
```

### Running Android application

Open project in Intellij IDEA or Android Studio and run "android" configuration.

### Running Web browser application

* To run, launch command: `./gradlew :web:jsBrowserDevelopmentRun`
* Or choose **browser** configuration in IDE and run it.  
  ![browser-run-configuration.png](screenshots/browser-run-configuration.png)

### Running iOS SwiftUI application

Open and build the Xcode project located in `ios` folder.

### Running iOS Compose Multiplatform application

Open and build the Xcode project located in `ios-compose` folder.

![Desktop](screenshots/todo.png)

BeaconLib
===

Adding it to your Android Studio project
---
BeaconLib is availiable through JCenter.

```GRADLE
dependencies {
    compile 'blue.walker:BeaconLib:0.1.+'
}
```

Adding it to your Eclipse Project
---
1. Clone the repository into your computer
2. Using the gradle wrapper, run the jarRelease task (./gradlew jarRelease)
3. Copy the 'BeaconLib.jar' located in 'build/libs' into your Eclipse project and use it as a library

Note: If the Gradle wrapper fails with the following error
```
A problem occurred configuring root project 'BeaconLib'.
> The SDK directory '' does not exist.
```
Simply set the ANDROID_HOME enviroment variable to the path to the Android SDK

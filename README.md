BeaconLib
===

Adding it to your Android Studio project
---
1. Clone the repository into your computer
2. Import the library into your project by going to File->Import Module
3. Choose the BeaconLib repository as the "Source directory" then click finish
4. In the build.gradle file for your application add the module as a dependency
```GRADLE
dependencies {
    compile project(':BeaconLib')
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

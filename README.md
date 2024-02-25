<br />
  <a href="https://github.com/othneildrew/Best-README-Template">
  <h1 align="center">Github Viewer Jetpack Compose Sample App</h1>
  
<!-- TABLE OF CONTENTS -->
## Table of Contents

* [About the Project](#about-the-project)
  * [Architecture](#architecture)
* [Features](#features)
* [Environment Setup](#requirements)
* [Contact](#contact)

  <!-- ABOUT THE PROJECT -->
## About The Project
  
This Sample Application was developed by using Jetpack Compose. Clean architecture and best practice are followed. You can find everything you need in the Jetpack Compose project within the application.  

  ## Architecture
Clean Architecture, MVVM (Model-View-ViewModel) architecture pattern has been used in the development of this application. The development language of the application is Kotlin.

* Architecture;
    * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
    * [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) 
    * [Navigation](https://developer.android.com/guide/navigation)
    * [Clean Architecture](https://developer.android.com/topic/architecture)

## Tech Stacks
This project uses many of the popular libraries, plugins and tools of the android ecosystem.
 
- [Compose](https://developer.android.com/jetpack/compose)
  
    - [Material3](https://developer.android.com/jetpack/androidx/releases/compose-material](https://developer.android.com/jetpack/compose/designsystems/material3)) - Build Jetpack Compose UIs with ready to use Material Design Components.
    - [Foundation](https://developer.android.com/jetpack/androidx/releases/compose-foundation) - Write Jetpack Compose applications with ready to use building blocks and extend foundation to build your own design system pieces.
    - [UI](https://developer.android.com/jetpack/androidx/releases/compose-ui) - Fundamental components of compose UI needed to interact with the device, including layout, drawing, and input.
    - [ConstraintLayout](https://developer.android.com/jetpack/androidx/releases/constraintlayout) - ConstraintLayout-compose 1.0 provides ConstraintLayout functionalities in Jetpack Compose.
    - [Lifecycle-ViewModel](https://developer.android.com/jetpack/androidx/releases/lifecycle) - Perform actions in response to a change in the lifecycle status of another component, such as activities and fragments.
    - [Coil](https://coil-kt.github.io/coil/compose/) - An image loading library for Android backed by Kotlin Coroutines.
  
  
- [Jetpack](https://developer.android.com/jetpack)
  
    - [AndroidX](https://developer.android.com/jetpack/androidx) - Major improvement to the original Android [Support Library](https://developer.android.com/topic/libraries/support-library/index), which is no longer maintained.
    - [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - Perform actions in response to a change in the lifecycle status of another component, such as activities and fragments.
    - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Designed to store and manage UI-related data in a lifecycle conscious way. The ViewModel class allows data to survive configuration changes such as screen rotations.
    - [Room](https://developer.android.com/training/data-storage/room) - Provides an abstraction layer over SQLite used for offline data caching.
    - [Paging3](https://developer.android.com/topic/libraries/architecture/paging/v3-overview) - The Paging Library makes it easier for you to load data gradually and gracefully within your app's [RecyclerView](https://developer.android.com/reference/androidx/recyclerview/widget/RecyclerView).
    
- Others
  
    - [Retrofit](https://square.github.io/retrofit/)
    - [OkHttp-Logging-Interceptor](https://github.com/square/okhttp/blob/master/okhttp-logging-interceptor/README.md) - Logs HTTP request and response data.
    - [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) - Library Support for coroutines.
    - [Flow](https://developer.android.com/kotlin/flow) - Flows are built on top of coroutines and can provide multiple values.
    - [Material Design](https://material.io/develop/android/docs/getting-started/) - Build awesome beautiful UIs.
    - [Gson](https://github.com/google/gson) - A modern JSON library for Kotlin and Java.
  
## Features
  - Users can save their favorite Github account.
  - The users can view all Github user's informations; such as avatar, bio, followers, following, account link, location(if any), name publicGists, publicRepos.
  - The users can see the list of all followers of a Github user with their avatar and userid, and can navigate to every followers's detail.
  - The users can search followers within the list of followers.
  - The users can change application theme.

  
## 👩‍💻 Contribution

All contributions are welcomed. Feel free to open PR. If you've found an error in this sample, please file an issue:</br>
<https://github.com/adityarai004/GithubViewerJetpack/issues>
</br>
PRs are encouraged.
  

## Environment Setup
  - First, make sure you have Android ```Bumblebee (2021.1.1)```  version installed
  - Android Studio's Gradle JDK version should be Java 11.0.10.
  
    
<!-- CONTACT -->
## Contact

- [Linkedin](https://www.linkedin.com/in/aditya-rai-149b3b230/)
- [Twitter](https://twitter.com/adityarai_004)

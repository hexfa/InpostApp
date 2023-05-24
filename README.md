# [![N|Solid][git]][hexfa] hexfa
## [![N|Solid][logo]][site] _Inpost Android App_
-----------------------------
* - * - *  * - * - * 
-----------------------------
## Details 📜

This Android project is a simple and lightweight application that allows users to view a list of postal shipments along with their respective status. The application reads the shipment data from a static JSON file and presents it to the users. With this app, users can easily track and monitor the status of their shipments.

The user interface of the app is designed to be simple and efficient, enabling users to effortlessly browse through the list of shipments and check their status. Additionally, users will be able to view the status of multiple shipments simultaneously, staying informed about the progress and latest updates of each shipment. The app aims to provide a user-friendly and cost-effective solution for users to conveniently track their shipments.

## Features 🚩

- Shipment list
- Parcel more details
- Archivement
- Archive list
- Rotatable
- Highlight parcels
- Sorting by: Status, PickupDate, ExpireDate, StoredDate, Number
- Pull to refresh
- Local database
- UnitTest
- Modern strcuture
- Modern libraries

## Lets Start 🕺

Clone this repository and import it into your **Android IDE**
```bash
https://github.com/hexfa/InpostApp.git
```

## Resources ℹ️
- #### Documents
  **`For looking at the code document of this project, you could generate it from the`** [Dokka section][dokka].
  
  **`Also, you could see the last documentation of the project on this link`** [Documentation][doc]

- #### Remote API
  ```bash
  This app doesn't have any remote API
  ```

- #### Structure
  **_Model-View-ViewModel (MVVM)_**

## Configuration ⚙️

- ### Dokka
  - _I recommend Html document type_
  - _I recommend to use the JAVA JDK 18 and set it in your JAVA_HOME environment. after putting it in the system needs a restart_

    - ###### Dokka terminal commands
      | Type | Syntax |
      | ------ | ------ |
      | Html | ./gradlew.bat dokkaHtml |
      | Java doc | ./gradlew.bat dokkaJavadoc |
      | Gfm | ./gradlew.bat dokkaGfm |

    - ###### Output Location
      `app -> build -> dokka`

### Keystore 🗝️
- Location of the keystore is in **root** directory(**inpost.jks**).
- Alias= inpost
- Key password= inpost2023 
- Key store password= inpost2023

### Publish App 📱
- Don't forget to update **versionCode** and **versionName** before uploading **.aab** file on Store.

- [APK link][link]

## Preview 🎞️
  ### Image 🖼️
  <img src="https://github.com/hexfa/Files/blob/main/ComingSoon.jpg" height="300" width="400" alt="Inpost App">
  
  ### Video 📽️
  <img src="https://github.com/hexfa/Files/blob/main/ComingSoon.jpg" height="300" width="400" alt="Inpost App">

## Built With 🛠
- [Kotlin][kotlin]: A modern and concise programming language for Android development.
 
- [Jetpack][jetpack]: Jetpack is a suite of libraries to help with app development and reduce boilerplate code. Using modern design practices, Jetpack libraries enable fewer crashes and memory leaks.

- [Coroutine][coroutine]: A Kotlin library that simplifies asynchronous programming and enables the writing of asynchronous code in a sequential manner.

- [Hilt][hilt]: A dependency injection library for Android that simplifies the process of injecting dependencies into Android components.

- [LiveData][live-data]: A lifecycle-aware data holder class in Android Jetpack that allows data to be observed for changes and provides automatic updates to the UI.

- [Dokka][dokka-site]: A documentation generation tool for Kotlin and Java that generates API documentation in various formats, including HTML and Markdown.

- [Junit][junit]: A popular unit testing framework for Java and Kotlin that provides a set of annotations and assertions for writing automated tests.

- [Room][room]: An SQLite database library for Android that provides an abstraction layer over SQLite and enables efficient database operations using annotations and generated code.

- [Moshi][moshi]: A modern JSON parsing and serialization library for Kotlin and Java that makes it easy to convert JSON data to Kotlin/Java objects and vice versa.

- [Mockito][mockito]: A mocking framework for unit testing in Java and Kotlin that allows the creation of mock objects to simulate dependencies and behavior during testing.

  ### 💡 Advice 💡
  In some cases, it may even be beneficial to use both **LiveData** and **Flow together**. You can use **Flow** for data processing and transformation in the   background, and then use **LiveData** to observe and update UI components based on the transformed data.

[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen.)

   [logo]: <https://github.com/hexfa/Files/blob/main/InpostApp/Logo/Icon.png> 
   [git]: <https://upload.wikimedia.org/wikipedia/commons/5/57/Iconoir_github-outline.svg>
   [site]: <https://inpost.pl>
   [hexfa]: <https://github.com/hexfa>
   [link]: <https://github.com/hexfa/>
   [dokka]: <#dokka>
   [doc]: <https://github.com/hexfa/Files/InpostApp/Documents>
   [hilt]: <https://dagger.dev/hilt/>
   [coroutine]: <https://kotlinlang.org/docs/coroutines-guide.html>
   [live-data]: <https://developer.android.com/topic/libraries/architecture/livedata>
   [dokka-site]: <https://kotlinlang.org/docs/dokka-gradle.html>
   [kotlin]: <https://kotlinlang.org/>
   [jetpack]: <https://developer.android.com/jetpack>
   [junit]: <https://junit.org/>
   [room]: <https://developer.android.com/training/data-storage/room>
   [moshi]: <https://github.com/square/moshi>
   [mockito]: <https://site.mockito.org/>

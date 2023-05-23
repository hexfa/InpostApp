# [![N|Solid][git]][hexfa] hexfa
## [![N|Solid][logo]][site] _Inpost Android App_
-----------------------------
* - * - *  * - * - * 
-----------------------------
## Details 📜

This Android project is a simple and lightweight application that allows users to view a list of postal shipments along with their respective status. The application reads the shipment data from a static JSON file and presents it to the users. With this app, users can easily track and monitor the status of their shipments.

The user interface of the app is designed to be simple and efficient, enabling users to effortlessly browse through the list of shipments and check their status. Additionally, users will be able to view the status of multiple shipments simultaneously, staying informed about the progress and latest updates of each shipment. The app aims to provide a user-friendly and cost-effective solution for users to conveniently track their shipments.

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
  **_MVVM_**

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
  ### Image 📷
  <img src="https://github.com/hexfa/Files/blob/main/ComingSoon.jpg" height="300" width="400" alt="Inpost App">
  
  ### Video 📽️
  <img src="https://github.com/hexfa/Files/blob/main/ComingSoon.jpg" height="300" width="400" alt="Inpost App">

## Built With 🛠
- [Coroutine][coroutine]
- [Hilt][hilt]
- [LiveData][live-data]
- [Dokka][dokka-site]


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

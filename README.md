# Upstox Portfolio Tracker

The Upstox Portfolio Tracker App leverages modern Android development techniques to provide a seamless experience for tracking stock investments. Using Hilt for dependency injection, Retrofit for network communication, and following the MVVM architecture, this app demonstrates best practices in Android development.

## Building the Project

To build the project, ensure you have Android Studio Arctic Fox or later and the Android SDK setup on your machine. Follow these steps:

1. Clone the repository to your local machine.
2. Open Android Studio and select "Open an Existing Project", navigating to the cloned repository.
3. Let Android Studio download any required dependencies.
4. Build the project by selecting "Build > Make Project".

## Architecture

The project follows the MVVM architecture pattern to separate the UI logic from the business logic, facilitating easier testing and maintenance. It is structured into several packages for clarity and separation of concerns:

- `data/`: Data handling (API interactions, models, repositories).
- `di/`: Dependency injection setup with Hilt.
- `domain/`: Business logic (controllers, domain models, utilities).
- `mappers/`: Data mapping between layers.
- `ui/`: User Interface components (Activities, Fragments, ViewModels).

## Key Components

- **Hilt** for dependency injection, simplifying the provision and management of dependencies across the application.
- **Retrofit** with **OkHttp** for making API calls and handling network communication.
- **Timber** for logging.
- **StateFlow** for handling asynchronous data streams in the ViewModel.
- **Android Jetpack Libraries** (LiveData, ViewModel, DataBinding) to implement the app according to the recommended best practices by Google.

## Dependencies

The project utilizes several dependencies to implement its functionality:

- AndroidX libraries for backward-compatible versions of Android components.
- Hilt for dependency injection.
- Retrofit and OkHttp for network requests.
- Gson for JSON parsing.
- Timber for logging.

For a full list of dependencies, refer to the `build.gradle` (Module: app) file.

## Multidex Support

Given the number of dependencies and the size of the project, MultiDex support is enabled to allow the app to exceed the 64K method count limit.

## Running the Application

To run the application:

1. Connect an Android device or use the Android Emulator.
2. Select the device from the target device dropdown in Android Studio.
3. Run the application by clicking the "Run" button or pressing Shift + F10.

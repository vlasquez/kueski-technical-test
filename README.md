# kueski-technical-test

Here's a sample README file for a movie app that consumes data from The Movie Database (TMDb) API and uses Android MVVM, Hilt, Retrofit, and Jetpack Compose.

# MovieApp

MovieApp is an Android application that allows users to show Popular and Playing Now Movies. The app uses The Movie Database (TMDb) API to fetch movie data and implements modern Android development practices including MVVM architecture, Hilt for dependency injection, Retrofit for networking, and Jetpack Compose for UI.


## Architecture

The app follows the MVVM (Model-View-ViewModel) architecture pattern to ensure a clear separation of concerns and testability. It also makes use of the following components:

- **Hilt**: For dependency injection
- **Retrofit**: For network calls to the TMDb API
- **Jetpack Compose**: For building the UI
- **Flow**: For observing data changes
- **ViewModel**: For managing UI-related data in a lifecycle-conscious way

## Getting Started

### Prerequisites

- Android Studio Bumblebee or higher
- A TMDb API key. The API key will be provided via email.

### Installation

1. Clone the repository:

   ```sh
   git clone https://github.com/yourusername/MovieApp.git
   
2. Open the project in Android Studio.
3. Add the file ``.kueski_credentials`` to your zshrc or bashrc, after that you need to restart or logout and login on your computer.
4. Verify that the api key is being loaded to your environment variables running the command ``echo MOVIES_API_KEY``
5. Build and run the app on an Android device or emulator.

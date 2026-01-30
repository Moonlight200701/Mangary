# Mangary

A modern Android manga application that fetches data from MangaDex API, built with **Jetpack Compose** and **Clean Architecture**.

## 🚀 Modern Tech Stack

- **UI**: Jetpack Compose with Material3
- **Architecture**: Clean Architecture + MVVM
- **Language**: Kotlin
- **DI**: Hilt (Dagger)
- **Networking**: Retrofit + OkHttp
- **Image Loading**: Coil Compose
- **Async**: Coroutines + StateFlow

## Architecture

This application follows **Clean Architecture** principles combined with **MVVM** (Model-View-ViewModel) pattern, ensuring a maintainable, testable, and scalable codebase.

### Project Structure

```
app/
├── data/                       # Data Layer
│   ├── remote/
│   │   ├── api/               # Retrofit API interfaces
│   │   │   └── MangaDexApiService.kt
│   │   └── dto/               # Data Transfer Objects
│   │       └── MangaDto.kt
│   └── repository/            # Repository implementations
│       └── MangaRepositoryImpl.kt
│
├── domain/                     # Domain Layer (Business Logic)
│   ├── model/                 # Domain entities
│   │   └── Manga.kt
│   ├── repository/            # Repository interfaces
│   │   └── MangaRepository.kt
│   └── usecase/               # Use cases (Business logic)
│       ├── GetMangaListUseCase.kt
│       ├── GetMangaByIdUseCase.kt
│       └── SearchMangaUseCase.kt
│
├── presentation/               # Presentation Layer (UI)
│   ├── theme/                 # Compose theme (Material3)
│   │   ├── Color.kt
│   │   ├── Theme.kt
│   │   └── Type.kt
│   └── manga/
│       └── list/
│           ├── MainActivity.kt
│           ├── MangaListViewModel.kt
│           └── MangaListScreen.kt  # Compose UI
│
└── di/                        # Dependency Injection
    └── AppModule.kt
```

## Clean Architecture Layers

### 1. Data Layer
- **Responsibilities**: Handle data operations, API calls, data mapping
- **Components**:
  - `MangaDexApiService`: Retrofit interface for API endpoints
  - `MangaDto`: Data transfer objects for API responses
  - `MangaRepositoryImpl`: Implementation of repository interface

### 2. Domain Layer
- **Responsibilities**: Business logic, independent of frameworks
- **Components**:
  - `Manga`: Domain model representing manga entity
  - `MangaRepository`: Interface defining data operations
  - `GetMangaListUseCase`: Use case for fetching manga list
  - `GetMangaByIdUseCase`: Use case for fetching manga details
  - `SearchMangaUseCase`: Use case for searching manga

### 3. Presentation Layer
- **Responsibilities**: UI logic, user interaction, state management
- **Components**:
  - `MainActivity`: Main entry point using Compose
  - `MangaListViewModel`: ViewModel with StateFlow for reactive UI
  - `MangaListScreen`: Composable UI components
  - **Theme**: Material3 theming system

## Technology Stack

- **Language**: Kotlin 1.9.25
- **UI Framework**: Jetpack Compose (Material3)
- **Architecture**: Clean Architecture + MVVM
- **Networking**: Retrofit 2.11.0 + OkHttp 4.12.0
- **Dependency Injection**: Hilt 2.52
- **Image Loading**: Coil Compose 2.7.0
- **Async**: Coroutines 1.9.0 + StateFlow
- **Build System**: Gradle 8.9 + AGP 8.7.3

## Features

- ✅ Browse manga list from MangaDex
- ✅ Search manga by title
- ✅ Pull to refresh
- ✅ Error handling and retry mechanism
- ✅ Loading states
- ✅ Modern Material3 UI
- ✅ Declarative UI with Compose
- ✅ Clean Architecture
- ✅ MVVM Pattern
- ✅ Dark mode support

## API

This app uses the [MangaDex API](https://api.mangadex.org/docs/) to fetch manga data.

Base URL: `https://api.mangadex.org/`

## Building the Project

1. Clone the repository
2. Open in Android Studio Hedgehog or later
3. Sync Gradle files
4. Run the app on an emulator or physical device

## Requirements

- Android Studio Hedgehog (2023.1.1) or later
- Minimum SDK: 24 (Android 7.0)
- Target SDK: 35 (Android 15)
- Kotlin 1.9.25+
- Java 17

## Documentation

- 📖 [Architecture Details](ARCHITECTURE.md)
- 📖 [Project Structure](PROJECT_STRUCTURE.md)
- 📖 [Gradle Updates](GRADLE_UPDATE.md)
- 📖 [Compose Migration](COMPOSE_MIGRATION.md)

## Jetpack Compose

This app is built entirely with Jetpack Compose, Google's modern declarative UI toolkit. No XML layouts are used. See [COMPOSE_MIGRATION.md](COMPOSE_MIGRATION.md) for details on the Compose implementation.

## License

This project is for educational purposes.
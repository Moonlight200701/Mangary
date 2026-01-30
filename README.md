# Mangary

A modern Android manga application that fetches data from MangaDex API.

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
│   └── manga/
│       └── list/
│           ├── MainActivity.kt
│           ├── MangaListViewModel.kt
│           └── MangaListAdapter.kt
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
  - `MainActivity`: Activity for displaying manga list
  - `MangaListViewModel`: ViewModel managing UI state
  - `MangaListAdapter`: RecyclerView adapter for manga items

## Technology Stack

- **Language**: Kotlin
- **Architecture**: Clean Architecture + MVVM
- **Networking**: Retrofit + OkHttp
- **Dependency Injection**: Hilt (Dagger)
- **Image Loading**: Coil
- **Async**: Coroutines + LiveData
- **UI**: Material Design Components

## Features

- ✅ Browse manga list from MangaDex
- ✅ Search manga by title
- ✅ View manga details
- ✅ Pull to refresh
- ✅ Error handling and retry mechanism
- ✅ Loading states
- ✅ Clean Architecture
- ✅ MVVM Pattern

## API

This app uses the [MangaDex API](https://api.mangadex.org/docs/) to fetch manga data.

Base URL: `https://api.mangadex.org/`

## Building the Project

1. Clone the repository
2. Open in Android Studio
3. Sync Gradle files
4. Run the app on an emulator or physical device

## Requirements

- Android Studio Arctic Fox or later
- Minimum SDK: 24 (Android 7.0)
- Target SDK: 34 (Android 14)
- Kotlin 1.9.0+
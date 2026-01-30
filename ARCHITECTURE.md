# Mangary - Clean Architecture Documentation

## Architecture Overview

This Android application follows Clean Architecture principles with MVVM pattern to ensure maintainability, testability, and scalability.

## Layer Separation

### Data Layer (Outermost)
**Location**: `app/src/main/java/com/mangary/app/data/`

**Responsibilities**:
- Handle external data sources (API, Database)
- Implement repository interfaces from domain layer
- Map DTOs to domain models
- Handle network errors

**Components**:
```
data/
├── remote/
│   ├── api/MangaDexApiService.kt      # Retrofit API interface
│   └── dto/MangaDto.kt                # Data Transfer Objects
└── repository/
    └── MangaRepositoryImpl.kt         # Repository implementation
```

**Key Principles**:
- Data layer knows about domain layer
- Uses DTOs (Data Transfer Objects) for API responses
- Implements mappers to convert DTOs to domain models

---

### Domain Layer (Core/Business Logic)
**Location**: `app/src/main/java/com/mangary/app/domain/`

**Responsibilities**:
- Define business logic
- Define repository interfaces
- Define domain models (entities)
- Independent of frameworks and UI

**Components**:
```
domain/
├── model/
│   └── Manga.kt                       # Domain entity
├── repository/
│   └── MangaRepository.kt             # Repository interface
└── usecase/
    ├── GetMangaListUseCase.kt         # Fetch manga list
    ├── GetMangaByIdUseCase.kt         # Fetch manga details
    └── SearchMangaUseCase.kt          # Search manga
```

**Key Principles**:
- Domain layer is independent - no Android/framework dependencies
- Use cases encapsulate business logic
- Repository interfaces define data contracts

---

### Presentation Layer (UI)
**Location**: `app/src/main/java/com/mangary/app/presentation/`

**Responsibilities**:
- Handle UI logic
- Manage UI state
- Display data to users
- Handle user interactions

**Components**:
```
presentation/
└── manga/
    └── list/
        ├── MainActivity.kt            # Activity (View)
        ├── MangaListViewModel.kt      # ViewModel
        └── MangaListAdapter.kt        # RecyclerView Adapter
```

**Key Principles**:
- MVVM pattern: Activity observes ViewModel
- ViewModel uses use cases from domain layer
- ViewModels survive configuration changes

---

## Dependency Injection (Hilt)
**Location**: `app/src/main/java/com/mangary/app/di/`

**Purpose**: Manage dependencies and provide them where needed

**Components**:
```
di/
└── AppModule.kt                       # Provides dependencies
```

**Provides**:
- OkHttpClient (with logging)
- Retrofit instance
- API service
- Repository implementation

---

## Data Flow

```
User Interaction (MainActivity)
         ↓
    ViewModel (MangaListViewModel)
         ↓
    Use Case (GetMangaListUseCase)
         ↓
    Repository Interface (MangaRepository)
         ↓
    Repository Implementation (MangaRepositoryImpl)
         ↓
    API Service (MangaDexApiService)
         ↓
    External API (MangaDex)
```

**Return Flow**:
```
MangaDex API Response
         ↓
    DTO (MangaDto)
         ↓
    Mapper (toDomainModel)
         ↓
    Domain Model (Manga)
         ↓
    Result<List<Manga>>
         ↓
    ViewModel (LiveData)
         ↓
    Activity (UI Update)
```

---

## Dependency Rules

1. **Domain Layer** (innermost):
   - No dependencies on other layers
   - Pure Kotlin/Java code
   - No Android framework dependencies

2. **Data Layer**:
   - Depends on domain layer
   - Implements domain interfaces
   - Uses Android framework for networking

3. **Presentation Layer**:
   - Depends on domain layer (use cases)
   - Uses Android framework (Activities, ViewModels)
   - Observes data from ViewModels

4. **Dependency Injection**:
   - Provides dependencies to all layers
   - Wires everything together

---

## Benefits of This Architecture

### 1. Separation of Concerns
- Each layer has a specific responsibility
- Changes in one layer don't affect others

### 2. Testability
- Domain layer can be tested without Android dependencies
- Repository can be mocked for ViewModel tests
- Use cases can be tested independently

### 3. Maintainability
- Clear structure makes code easy to understand
- Changes are localized to specific layers
- New features follow established patterns

### 4. Scalability
- Easy to add new features following the same pattern
- Can swap implementations without affecting other layers
- Can add new data sources easily

### 5. Independence
- UI independent of business logic
- Business logic independent of frameworks
- Data sources can be changed without affecting business logic

---

## Example: Adding a New Feature

**To add "Favorites" feature:**

1. **Domain Layer**:
   - Add `FavoriteRepository` interface
   - Add `AddToFavoritesUseCase`
   - Add `GetFavoritesUseCase`

2. **Data Layer**:
   - Add local database (Room)
   - Implement `FavoriteRepositoryImpl`
   - Add database entities and DAOs

3. **Presentation Layer**:
   - Add `FavoritesViewModel`
   - Add `FavoritesActivity`
   - Update existing screens with favorite button

4. **DI Layer**:
   - Provide database instance
   - Provide repository implementation

This demonstrates how the architecture allows adding features without modifying existing code significantly.

---

## Technology Stack

- **Language**: Kotlin
- **Architecture**: Clean Architecture + MVVM
- **DI**: Hilt (Dagger)
- **Networking**: Retrofit + OkHttp
- **Async**: Coroutines + LiveData
- **Image Loading**: Coil
- **UI**: Material Design Components

---

## Best Practices Implemented

1. ✅ Single Responsibility Principle - Each class has one job
2. ✅ Dependency Inversion - Depend on abstractions, not implementations
3. ✅ Interface Segregation - Use cases are focused and specific
4. ✅ Repository Pattern - Abstract data sources
5. ✅ MVVM Pattern - Separate UI logic from business logic
6. ✅ Dependency Injection - Manage dependencies centrally
7. ✅ Error Handling - Result wrapper for success/error states
8. ✅ Coroutines - Handle async operations cleanly

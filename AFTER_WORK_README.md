# After Work

## Description
This Android project is structured according to the principles of **Clean Architecture**.
I didn't implement models for domain layer. In a more advanced, less time constrained project, I would have done it.

It uses :
- **Retrofit** for network requests
- **Hilt** for dependency injection
- **Jetpack Compose** for the UI layer
- **Coroutines** for asynchronous programming (with SharedFlow and StateFlow)
- **Compose Navigation** for easier UI navigation with Compose
- **Coil** for image loading
- **Mockk** for mocking objects in tests
- **Room** for local storage
- **JUnit5** for unit tests
- **Material Design 3** for UI components

Some UT Tests are implemented in the **network** & **usecase** layers.
With a bit more time, I would have implemented more tests in the **app** module. 
However, the viewModels and the main UI screen is tested. 

## Architecture
The architecture consists of multiple modules, representing a layer of the Clean Architecture :
- **app** module (UI Layer) contains the UI layer with a classic MVVM architecture. The ViewModel hold the data and exposes it to the UI layer.
- **useCase** module (Domain Layer) contains the business logic
- **network** module (Data Layer) contains two submodules. **api** contains the models and API repository interface. **retrofit** implements repository and fetch the data from rest API.
- **storage** module (Data layer) contains the local storage implementation with Room.

Note : Only the **app** & **room** module are Android module. The other modules are pure Kotlin modules.

## Improvements
- The network error handling is not optimal. I would have expanded the http status codes in RetrofitNetworkResultExt
- The error handling is not implemented in room module. It's an optimistic implementation but in a real world, a proper management would be needed. 
- With model classes in the domain layer, the project would be more compliant with Clean Architecture principles.

## Screenshots video

<img alt="Demo video" height="800" width="360" src="demo.gif"/>



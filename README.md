# Rides

Simple Android App using Random Data API based on MVVM and clean architecture, It fetches the data from the API in order to display random vehicle list, and when the user clicks on an item, it opens basic details of the vehicle on the second screen, and when a button click on the second screen it display a bottom sheet with carbon emission.

## Features:

* The first screen is a RecyclerView containing vehicles 
* The second screen is the selected vehicle details screen
* A pull down scroll to retrieve a new list of vehicles
* Bottom Sheet to display carbon emission

## Technologies Used

Project is created with:

* [100% Kotlin] we use Kotlin over java because it's safer and concise
* [Model View viewModel] we use Mvvm as an architecture pattern of our project
* [Clean Architecture] Clean Architecture is a core architecture for many applications
* [LiveData & Flow] we use LiveData and Flow as our observables data holder class.
* [Retrofit (with Coroutines)] we use retrofit with coroutines to communicate with the rest API and the paging 3 library take care of launching these coroutines
* [Gson] we use the Gson library to convert a JSON string to an equivalent Java object.
* [ViewModel] we use viewModel as a data manager in our application
* [Navigation Component with safe args] we use the navigation component to navigate from the vehicles list to the detail screen.
* [View Binding] we use ViewBinding to access our views in a compile-time safe way
* [Dagger-Hilt] we use dagger-hilt for Dependency Injection.

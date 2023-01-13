package com.example.rides.core

sealed class Resource<T>(val data: T? = null, val message: UniversalText? = null) {
    class Loading<T>(data: T? = null): Resource<T>(data)
    class Success<T>(data: T?): Resource<T>(data)
    class Error<T>(message: UniversalText, data: T? = null): Resource<T>(data, message)
}

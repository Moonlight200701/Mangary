package com.example.mangary3.core

sealed class UiState<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T?) : UiState<T>(data = data)
    class Error<T>(data: T?, message: String) : UiState<T>(data = data, message = message)
    class Loading<T>(data: T?, message: String): UiState<T>(data = data, message = message)
}
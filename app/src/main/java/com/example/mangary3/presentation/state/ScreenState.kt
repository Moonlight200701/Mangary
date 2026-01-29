package com.example.mangary3.presentation.state

sealed class ScreenState {
    data class Success(val data: Any) : ScreenState()
    data class Error(val message: String) : ScreenState()
    object Loading : ScreenState()

}
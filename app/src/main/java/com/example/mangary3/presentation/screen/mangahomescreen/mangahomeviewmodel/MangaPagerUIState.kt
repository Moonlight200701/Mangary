package com.example.mangary3.presentation.screen.mangahomescreen.mangahomeviewmodel

import com.example.mangary3.domain.model.Manga

sealed interface MangaHomeScreenUIState {
    data object Initial : MangaHomeScreenUIState

    data object Loading : MangaHomeScreenUIState

    data class Success(
        val mangaList: List<Manga>,
        val selectedCategory: String = "All",
        val searchQuery: String = ""
    ) : MangaHomeScreenUIState

    data class Refreshing(
        val mangaList: List<Manga>, // Keep showing current data while refreshing
        val selectedCategory: String = "All",
        val searchQuery: String = ""
    ) : MangaHomeScreenUIState

    data class Error(
        val message: String,
        val selectedCategory: String = "All",
        val searchQuery: String = ""
    ) : MangaHomeScreenUIState
}
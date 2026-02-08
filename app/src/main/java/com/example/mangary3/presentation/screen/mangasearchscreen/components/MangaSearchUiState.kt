package com.example.mangary3.presentation.screen.mangasearchscreen.components

import com.example.mangary3.domain.model.Manga

sealed class MangaSearchUiState {
    data class Loading(val searchQuery: String) : MangaSearchUiState()
    data class Success(val mangas: List<Manga>) : MangaSearchUiState()
    data class Error(val message: String) : MangaSearchUiState()
}
package com.example.mangary3.presentation.screen.mangasearchscreen.mangadetailviewmodel

import com.example.mangary3.domain.model.Manga

sealed class MangaSearchUiState {
    data object Initial : MangaSearchUiState()
    data object Loading : MangaSearchUiState()
    data class Success(
        val mangas: List<Manga>
    ) : MangaSearchUiState()
}
package com.example.mangary3.presentation.state

import com.example.mangary3.domain.model.Manga

data class MangaPagerUiState(
    val mangaList: List<Manga> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val errorMessage: String? = null,
    val selectedCategory: String = "All",
    val searchQuery: String = ""
)
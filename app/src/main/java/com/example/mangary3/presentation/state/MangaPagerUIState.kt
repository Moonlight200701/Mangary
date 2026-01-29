package com.example.mangary3.presentation.state

import com.example.mangary3.data.remote.responses.MangaFromAPIDTO

data class MangaPagerUiState(
    val mangaList: List<MangaFromAPIDTO> = emptyList(),
    val isLoading: Boolean = true,
    val isLoadMore: Boolean = false,
    val errorMessage: String? = null
)
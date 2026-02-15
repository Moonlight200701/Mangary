package com.example.mangary3.presentation.screen.mangasearchscreen.mangadetailviewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mangary3.domain.usecases.SearchMangaUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MangaSearchViewModel @Inject constructor(
    private val searchMangaUseCase: SearchMangaUseCase,
) : ViewModel() {
    fun searchManga(title: String) =
        viewModelScope.launch {
            searchMangaUseCase(title)
        }
}

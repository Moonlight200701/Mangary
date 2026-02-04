package com.example.mangary3.presentation.viewmodel.mangahomeviewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mangary3.domain.usecases.FetchAllTagsUseCase
import com.example.mangary3.domain.usecases.FetchMangaListUseCase
import com.example.mangary3.presentation.state.MangaPagerUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MangaHomeViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val fetchMangaListUseCase: FetchMangaListUseCase,
    private val fetchAllTagsUseCase: FetchAllTagsUseCase,
) : ViewModel() {
    private val _mangaPagerUiState = MutableStateFlow(MangaPagerUiState())
    val mangaPagerUiState = _mangaPagerUiState.asStateFlow()

    private val _tags = MutableStateFlow<List<String>>(emptyList())
    val tags: StateFlow<List<String>> = _tags.asStateFlow()


    init {
        loadMangaList()
    }

    private fun loadMangaList() {
        viewModelScope.launch(Dispatchers.IO) {
            _mangaPagerUiState.update { it.copy(isLoading = true) }
            try {
                val mangas = fetchMangaListUseCase()
                Timber.d("Manga size: $mangas")
                val mangaTags = fetchAllTagsUseCase()
                _tags.value = mangaTags.flatMap { it.attributes.name.values.toList() }
                _mangaPagerUiState.update {
                    it.copy(mangaList = mangas, isLoading = false, errorMessage = null)
                }
            } catch (e: Exception) {
                _mangaPagerUiState.update {
                    it.copy(isLoading = false, errorMessage = "Failed to load manga: ${e.message}")
                }
            }
        }
    }

    fun refresh() {
        viewModelScope.launch {
            _mangaPagerUiState.update { it.copy(isRefreshing = true) }
            try {
                val mangas = fetchMangaListUseCase()
                _mangaPagerUiState.update {
                    it.copy(mangaList = mangas, isRefreshing = false, errorMessage = null)
                }
            } catch (e: Exception) {
                _mangaPagerUiState.update {
                    it.copy(isRefreshing = false, errorMessage = "Failed to refresh")
                }
            }
        }
    }

    fun updateCategory(category: String) {
        _mangaPagerUiState.update { it.copy(selectedCategory = category) }
    }

    fun updateSearchQuery(query: String) {
        _mangaPagerUiState.update { it.copy(searchQuery = query) }
    }
}
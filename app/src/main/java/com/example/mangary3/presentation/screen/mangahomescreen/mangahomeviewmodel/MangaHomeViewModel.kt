package com.example.mangary3.presentation.screen.mangahomescreen.mangahomeviewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mangary3.domain.repository.NetworkRepository
import com.example.mangary3.domain.usecases.FetchAllTagsUseCase
import com.example.mangary3.domain.usecases.FetchMangaListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MangaHomeViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val fetchMangaListUseCase: FetchMangaListUseCase,
    private val fetchAllTagsUseCase: FetchAllTagsUseCase,
    private val networkRepository: NetworkRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<MangaHomeScreenUIState>(MangaHomeScreenUIState.Initial)
    val uiState: StateFlow<MangaHomeScreenUIState> = _uiState.asStateFlow()

    private val _tags = MutableStateFlow<List<String>>(emptyList())
    val tags: StateFlow<List<String>> = _tags.asStateFlow()

    val networkStatus: StateFlow<Boolean> = networkRepository.networkStatus.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = true
    )

    init {
        loadManga()
    }

    private fun loadManga() {
        _uiState.value = MangaHomeScreenUIState.Loading

        viewModelScope.launch {
            try {
                val mangas = fetchMangaListUseCase()
                val tags = fetchAllTagsUseCase()

                _uiState.value = MangaHomeScreenUIState.Success(mangas)
                _tags.value = tags
            } catch (e: Exception) {
                _uiState.value = MangaHomeScreenUIState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun refresh() {
        val currentState = _uiState.value
        if (currentState is MangaHomeScreenUIState.Success) {
            _uiState.value = MangaHomeScreenUIState.Refreshing(
                mangaList = currentState.mangaList,
                selectedCategory = currentState.selectedCategory,
                searchQuery = currentState.searchQuery
            )
        }

        viewModelScope.launch {
            try {
                val mangas = fetchMangaListUseCase()
                val tags = fetchAllTagsUseCase()

                _uiState.value = MangaHomeScreenUIState.Success(
                    mangaList = mangas,
                    selectedCategory = (currentState as? MangaHomeScreenUIState.Success)?.selectedCategory
                        ?: "All",
                    searchQuery = (currentState as? MangaHomeScreenUIState.Success)?.searchQuery
                        ?: ""
                )
                _tags.value = tags
            } catch (e: Exception) {
                _uiState.value = MangaHomeScreenUIState.Error(e.message ?: "Unknown error")
            }
        }
    }
}

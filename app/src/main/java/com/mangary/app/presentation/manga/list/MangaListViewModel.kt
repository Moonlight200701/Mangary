package com.mangary.app.presentation.manga.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mangary.app.domain.model.Manga
import com.mangary.app.domain.model.Result
import com.mangary.app.domain.usecase.GetMangaListUseCase
import com.mangary.app.domain.usecase.SearchMangaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for manga list screen
 * Part of presentation layer - handles UI logic and state management
 */
@HiltViewModel
class MangaListViewModel @Inject constructor(
    private val getMangaListUseCase: GetMangaListUseCase,
    private val searchMangaUseCase: SearchMangaUseCase
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(MangaListUiState())
    val uiState: StateFlow<MangaListUiState> = _uiState.asStateFlow()
    
    private var currentOffset = 0
    private val limit = 20
    
    init {
        loadMangaList()
    }
    
    /**
     * Load manga list from API
     */
    fun loadMangaList() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            when (val result = getMangaListUseCase(limit, currentOffset)) {
                is Result.Success -> {
                    _uiState.value = _uiState.value.copy(
                        mangaList = result.data,
                        isLoading = false,
                        error = null
                    )
                }
                is Result.Error -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = result.message
                    )
                }
                is Result.Loading -> {
                    // Loading state managed by isLoading
                }
            }
        }
    }
    
    /**
     * Search manga by query
     */
    fun searchManga(query: String) {
        if (query.isBlank()) {
            loadMangaList()
            return
        }
        
        currentOffset = 0  // Reset offset when searching
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            when (val result = searchMangaUseCase(query, limit, 0)) {
                is Result.Success -> {
                    _uiState.value = _uiState.value.copy(
                        mangaList = result.data,
                        isLoading = false,
                        error = null
                    )
                }
                is Result.Error -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = result.message
                    )
                }
                is Result.Loading -> {
                    // Loading state managed by isLoading
                }
            }
        }
    }
    
    /**
     * Retry loading on error
     */
    fun retry() {
        currentOffset = 0
        loadMangaList()
    }
}

/**
 * UI State for manga list screen
 */
data class MangaListUiState(
    val mangaList: List<Manga> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

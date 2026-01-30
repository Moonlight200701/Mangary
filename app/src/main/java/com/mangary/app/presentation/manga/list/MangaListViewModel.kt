package com.mangary.app.presentation.manga.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mangary.app.domain.model.Manga
import com.mangary.app.domain.model.Result
import com.mangary.app.domain.usecase.GetMangaListUseCase
import com.mangary.app.domain.usecase.SearchMangaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
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
    
    private val _mangaList = MutableLiveData<List<Manga>>()
    val mangaList: LiveData<List<Manga>> = _mangaList
    
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error
    
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
            _isLoading.value = true
            when (val result = getMangaListUseCase(limit, currentOffset)) {
                is Result.Success -> {
                    _mangaList.value = result.data
                    _error.value = ""
                }
                is Result.Error -> {
                    _error.value = result.message
                }
                is Result.Loading -> {
                    // Already handled
                }
            }
            _isLoading.value = false
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
        
        viewModelScope.launch {
            _isLoading.value = true
            when (val result = searchMangaUseCase(query, limit, 0)) {
                is Result.Success -> {
                    _mangaList.value = result.data
                    _error.value = ""
                }
                is Result.Error -> {
                    _error.value = result.message
                }
                is Result.Loading -> {
                    // Already handled
                }
            }
            _isLoading.value = false
        }
    }
    
    /**
     * Load more manga (pagination)
     */
    fun loadMoreManga() {
        currentOffset += limit
        viewModelScope.launch {
            when (val result = getMangaListUseCase(limit, currentOffset)) {
                is Result.Success -> {
                    val currentList = _mangaList.value ?: emptyList()
                    _mangaList.value = currentList + result.data
                }
                is Result.Error -> {
                    _error.value = result.message
                }
                is Result.Loading -> {
                    // Already handled
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

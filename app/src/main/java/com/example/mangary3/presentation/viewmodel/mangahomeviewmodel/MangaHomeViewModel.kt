package com.example.mangary3.presentation.viewmodel.mangahomeviewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mangary3.domain.usecases.FetchMangaListUseCase
import com.example.mangary3.presentation.state.MangaPagerUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MangaHomeViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val fetchMangaListUseCase: FetchMangaListUseCase
) : ViewModel() {
    private val _mangaPagerUiState = MutableStateFlow(MangaPagerUiState())
    val mangaPagerUiState = _mangaPagerUiState.asStateFlow()

    init {
        loadMangaList()
    }

    private fun loadMangaList() {
        viewModelScope.launch {
            _mangaPagerUiState.update { it.copy(isLoading = true) }
            try {
                val mangas = fetchMangaListUseCase()
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

//    fun loadMoreManga() {
//        viewModelScope.launch {
//            _mangaPagerUiState.update { it.copy(isLoadMore = true) }
//            try {
//                val response = mangaAPIService.getMangaList(limit = 10, offset = uiState.value.offset + 10)
//                _mangaPagerUiState.update {
//                    it.copy(
//                        mangaList = it.mangaList + response.data,
//                        isLoadingMore = false,
//                        offset = it.offset + 10
//                    )
//                }
//            } catch (e: Exception) {
//                _mangaPagerUiState.update { it.copy(isLoadingMore = false) }
//            }
//        }
//    }
}
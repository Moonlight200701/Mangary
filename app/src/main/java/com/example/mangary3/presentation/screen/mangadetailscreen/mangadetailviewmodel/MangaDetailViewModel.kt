package com.example.mangary3.presentation.screen.mangadetailscreen.mangadetailviewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mangary3.domain.model.Manga
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class MangaDetailUIState {
    object Initial : MangaDetailUIState()
    object Loading : MangaDetailUIState()
    data class Success(val manga: Manga) : MangaDetailUIState()
    data class Error(val message: String) : MangaDetailUIState()
}

@HiltViewModel
class MangaDetailViewModel @Inject constructor(
    // TODO: Inject GetMangaByIdUseCase when implemented
) : ViewModel() {

    private val _uiState = MutableStateFlow<MangaDetailUIState>(MangaDetailUIState.Initial)
    val uiState: StateFlow<MangaDetailUIState> = _uiState.asStateFlow()

    fun loadMangaDetails(mangaId: String) {
        viewModelScope.launch {
            _uiState.value = MangaDetailUIState.Loading
            try {
                // TODO: Call use case to get full manga details by ID
                // val manga = getMangaByIdUseCase(mangaId)
                // _uiState.value = MangaDetailUIState.Success(manga)
            } catch (e: Exception) {
                _uiState.value = MangaDetailUIState.Error(e.message ?: "Unknown error")
            }
        }
    }
}


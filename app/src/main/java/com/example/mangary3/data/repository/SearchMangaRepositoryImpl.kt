package com.example.mangary3.data.repository

import com.example.mangary3.data.remote.MangaAPIService
import com.example.mangary3.data.remote.dto.MangaDTO
import com.example.mangary3.domain.repository.SearchMangaRepository
import jakarta.inject.Inject
import timber.log.Timber

class SearchMangaRepositoryImpl @Inject constructor(
    private val mangaApi: MangaAPIService
) : SearchMangaRepository {
    override suspend fun searchManga(title: String): List<MangaDTO> =
        try {
            mangaApi.searchMangasFromMangaDex().data
        } catch (e: Exception) {
            Timber.e(e)
            emptyList()
        }
}

package com.example.mangary3.data.repository

import com.example.mangary3.data.remote.MangaAPIService
import com.example.mangary3.data.remote.dto.MangaDTO
import com.example.mangary3.data.remote.dto.MangaTagDTO
import com.example.mangary3.domain.repository.GeneralMangaRepository
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GeneralMangaRepositoryImpl @Inject constructor(private val mangaApi: MangaAPIService) :
    GeneralMangaRepository {
    override suspend fun getMangas(): List<MangaDTO> =
        try {
            Timber.d("${mangaApi.getMangasFromMangaDex().data.size}")
            mangaApi.getMangasFromMangaDex().data
        } catch (e: Exception) {
            Timber.e(e)
            emptyList()
        }

    override suspend fun getMangaByGenre(): List<MangaDTO> {
        TODO("Not yet implemented")
    }

    override suspend fun getMangaByTitle(): List<MangaDTO> {
        TODO("Not yet implemented")
    }

    override suspend fun getMangaByRating(): List<MangaDTO> {
        TODO("Not yet implemented")
    }

    override suspend fun getMangaByAuthor(): List<MangaDTO> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllTags(): List<MangaTagDTO> =
        try {
            Timber.d("${mangaApi.getAllTags().data.size}")
            mangaApi.getAllTags().data
        } catch (e: Exception) {
            Timber.e(e)
            emptyList()
        }

}
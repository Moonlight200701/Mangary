package com.example.mangary3.data.repository

import com.example.mangary3.data.remote.MangaAPIService
import com.example.mangary3.data.remote.responses.MangaFromAPIDTO
import com.example.mangary3.domain.repository.GeneralMangaRepository
import timber.log.Timber
import javax.inject.Inject

class GeneralMangaRepositoryImpl @Inject constructor(private val mangaApi: MangaAPIService) :
    GeneralMangaRepository {
    override suspend fun getMangas(): List<MangaFromAPIDTO> =
        try {
            mangaApi.getMangasFromSource().data
        } catch (e: Exception) {
            Timber.e(e)
            emptyList()
        }

    override suspend fun getMangaByGenre(): List<MangaFromAPIDTO> {
        TODO("Not yet implemented")
    }

    override suspend fun getMangaByTitle(): List<MangaFromAPIDTO> {
        TODO("Not yet implemented")
    }

    override suspend fun getMangaByRating(): List<MangaFromAPIDTO> {
        TODO("Not yet implemented")
    }

    override suspend fun getMangaByAuthor(): List<MangaFromAPIDTO> {
        TODO("Not yet implemented")
    }
}
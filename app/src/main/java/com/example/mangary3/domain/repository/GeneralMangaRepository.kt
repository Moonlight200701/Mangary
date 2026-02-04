package com.example.mangary3.domain.repository

import com.example.mangary3.data.remote.dto.MangaDTO
import com.example.mangary3.data.remote.dto.MangaTagDTO

interface GeneralMangaRepository {
    suspend fun getMangas(): List<MangaDTO>

    suspend fun getMangaByGenre(): List<MangaDTO>

    suspend fun getMangaByTitle(): List<MangaDTO>

    suspend fun getMangaByRating(): List<MangaDTO>

    suspend fun getMangaByAuthor(): List<MangaDTO>

    suspend fun getAllTags(): List<MangaTagDTO>
}
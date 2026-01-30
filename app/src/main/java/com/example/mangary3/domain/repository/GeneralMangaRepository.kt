package com.example.mangary3.domain.repository

import com.example.mangary3.data.remote.responses.MangaFromAPIDTO

interface GeneralMangaRepository {
    suspend fun getMangas(): List<MangaFromAPIDTO>

    suspend fun getMangaByGenre(): List<MangaFromAPIDTO>

    suspend fun getMangaByTitle(): List<MangaFromAPIDTO>

    suspend fun getMangaByRating(): List<MangaFromAPIDTO>

    suspend fun getMangaByAuthor(): List<MangaFromAPIDTO>
}
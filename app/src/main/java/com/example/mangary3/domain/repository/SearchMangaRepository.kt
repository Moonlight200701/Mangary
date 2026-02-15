package com.example.mangary3.domain.repository

import com.example.mangary3.data.remote.dto.MangaDTO

interface SearchMangaRepository {
    suspend fun searchManga(title: String): List<MangaDTO>
}
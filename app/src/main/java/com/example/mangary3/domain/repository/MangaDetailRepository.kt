package com.example.mangary3.domain.repository

interface MangaDetailRepository {
    suspend fun getCurrentMangaDescription()
}
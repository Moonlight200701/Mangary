package com.example.mangary3.data.remote.dto

data class TagResponseDTO(
    val result: String,
    val response: String,
    val data: List<MangaTagDTO>
)
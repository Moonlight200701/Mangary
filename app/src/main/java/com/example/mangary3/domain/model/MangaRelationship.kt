package com.example.mangary3.domain.model

data class MangaRelationship(
    val type: String,
    val attributes: CoverAttributes?
)

data class CoverAttributes(
    val fileName: String
)
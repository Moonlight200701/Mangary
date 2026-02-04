package com.example.mangary3.domain.model

data class Manga(
    val id: String,
    val type: String,
    val attributes: MangaAttributes,
    val relationship: List<MangaRelationship> = emptyList()
) {
    fun getCoverArtUrl(): String? {
        val coverart = relationship.find { it.type == "cover_art" }
        val fileName = coverart?.attributes?.fileName ?: return null
        return ""
    }
}
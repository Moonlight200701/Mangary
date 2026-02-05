package com.example.mangary3.domain.model

import com.example.mangary3.core.Constants
import timber.log.Timber

data class Manga(
    val id: String,
    val type: String,
    val attributes: MangaAttributes,
    val relationship: List<MangaRelationship> = emptyList()
) {
    fun getCoverArtUrl(): String? {
        try {
            val coverArt = relationship.find { it.type == Constants.MANGA_COVER_ART_KEY }
            val fileName = coverArt?.attributes?.fileName ?: return null
            return Constants.MANGA_COVER_IMAG + id + "/" + fileName
        } catch (e: Exception) {
            Timber.e("Fail to get cover art: $e")
            return null
        }
    }
}
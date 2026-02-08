package com.example.mangary3.domain.model

import com.example.mangary3.core.constants.APIConstants
import timber.log.Timber

data class Manga(
    val id: String,
    val type: String,
    val attributes: MangaAttributes,
    val relationship: List<MangaRelationship> = emptyList()
) {
    fun getCoverArtUrl(): String? {
        try {
            val coverArt = relationship.find { it.type == APIConstants.MANGA_COVER_ART_KEY }
            val fileName = coverArt?.attributes?.fileName
            if (fileName.isNullOrEmpty()) {
                Timber.e("Cover art fileName is null or empty for manga id: $id")
                return null
            }
            Timber.d("Cover art URL: ${APIConstants.MANGA_COVER_IMAG + id + "/" + fileName}")
            return APIConstants.MANGA_COVER_IMAG + id + "/" + fileName
        } catch (e: Exception) {
            Timber.e("Fail to get cover art: $e")
            return null
        }
    }
}
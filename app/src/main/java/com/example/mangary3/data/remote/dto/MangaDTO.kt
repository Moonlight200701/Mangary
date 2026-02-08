package com.example.mangary3.data.remote.dto

import com.example.mangary3.core.constants.MangaConstants
import com.google.gson.annotations.SerializedName

data class MangaDTO(
    val id: String,
    val type: String,
    @SerializedName(MangaConstants.MANGA_RELATIONSHIP_ATTRIBUTES_KEY) val attributes: MangaAttributesDTO,
    @SerializedName(MangaConstants.MANGA_RELATIONSHIP_KEY) val relationship: List<MangaRelationshipDTO>
)
package com.example.mangary3.data.remote.dto

import com.example.mangary3.core.constants.MangaConstants
import com.google.gson.annotations.SerializedName

data class MangaRelationshipDTO(
    @SerializedName(MangaConstants.MANGA_RELATIONSHIP_ID_KEY) val id: String,
    @SerializedName(MangaConstants.MANGA_RELATIONSHIP_TYPE_KEY) val type: String,
    @SerializedName(MangaConstants.MANGA_RELATIONSHIP_ATTRIBUTES_KEY) val attributes: CoverAttributesDTO?
)

data class CoverAttributesDTO(
    @SerializedName(MangaConstants.MANGA_RELATIONSHIP_FILE_NAME_KEY) val fileName: String
)
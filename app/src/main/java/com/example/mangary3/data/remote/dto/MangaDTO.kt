package com.example.mangary3.data.remote.dto

import com.google.gson.annotations.SerializedName

data class MangaDTO(
    val id: String,
    val type: String,
    @SerializedName("attributes") val attributes: MangaAttributesDTO,
    @SerializedName("relationships") val relationship: List<MangaRelationshipDTO>
)
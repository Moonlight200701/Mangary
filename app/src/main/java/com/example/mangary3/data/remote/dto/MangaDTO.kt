package com.example.mangary3.data.remote.dto

import com.example.mangary3.domain.model.MangaRelationship
import com.google.gson.annotations.SerializedName

data class MangaDTO(
    val id: String,
    val type: String,
    @SerializedName("attributes") val attributes: MangaAttributesDTO,
    @SerializedName("relationships") val relationship: MangaRelationshipDTO
)
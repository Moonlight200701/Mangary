package com.example.mangary3.data.remote.dto

import com.google.gson.annotations.SerializedName

data class MangaRelationshipDTO (
    val id: String,
    val type: String,
    @SerializedName("attributes") val attributes: CoverAttributesDTO?
)

data class CoverAttributesDTO(
    @SerializedName("filename") val fileName: String
)
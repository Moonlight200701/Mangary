package com.example.mangary3.data.remote.dto

import com.google.gson.annotations.SerializedName

data class MangaTagDTO(
    @SerializedName("id") val id: String,
    @SerializedName("type") val type: String,
    @SerializedName("attributes") val attributes: MangaTagAttributesDTO
)
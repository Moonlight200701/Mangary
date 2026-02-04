package com.example.mangary3.domain.model

import com.google.gson.annotations.SerializedName

data class MangaTag (
    @SerializedName("id") val id: String,
    @SerializedName("type") val type: String,
    @SerializedName("attributes") val attributes: MangaTagAttributes
)
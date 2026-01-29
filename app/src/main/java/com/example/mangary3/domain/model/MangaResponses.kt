package com.example.mangary3.domain.model

import com.google.gson.annotations.SerializedName

data class MangaFromAPI(
    val id: String,
    val type: String,
    val attributes: MangaAttributes
)

data class MangaAttributes(
    val title: Map<String, String>,
    val description: Map<String, String>
)

data class MangaResponses(
    @SerializedName("result") val result: String,
    @SerializedName("response") val response: String,
    @SerializedName("data") val data: List<MangaFromAPI>,
    @SerializedName("tag") val tag: List<MangaTags>
)

data class MangaTags(
    val id: String,
    val type: String,
    val attributes: MangaTagAttributes
)

data class MangaTagAttributes (
    val name: Map<String, String>
)
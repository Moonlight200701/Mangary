package com.example.mangary3.data.remote.responses

import com.google.gson.annotations.SerializedName
// DTO
data class MangaFromAPIDTO(
    val id: String,
    val type: String,
    val attributes: MangaAttributesDTO
)

data class MangaAttributesDTO(
    val title: Map<String, String>,
    val description: Map<String, String>
)

data class MangaResponses(
    @SerializedName("result") val result: String,
    @SerializedName("response") val response: String,
    @SerializedName("data") val data: List<MangaFromAPIDTO>,
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
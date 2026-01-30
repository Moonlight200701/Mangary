package com.example.mangary3.data

import com.google.gson.annotations.SerializedName

data class MangaFromAPIDTO(
    val id: String,
    val type: String,
    val attributes: MangaAttributesDTO
)

data class MangaAttributesDTO(
    val title: Map<String, String>,
    val description: Map<String, String>
)

data class MangaResponsesDTO(
    @SerializedName("result") val result: String,
    @SerializedName("response") val response: String,
    @SerializedName("data") val data: List<MangaFromAPIDTO>,
    @SerializedName("tag") val tag: List<MangaTagsDTO>
)

data class MangaTagsDTO(
    val id: String,
    val type: String,
    val attributes: MangaTagAttributesDTO
)

data class MangaTagAttributesDTO (
    val name: Map<String, String>
)
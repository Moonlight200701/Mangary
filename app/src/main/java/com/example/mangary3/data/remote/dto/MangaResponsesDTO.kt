package com.example.mangary3.data.remote.dto

import com.google.gson.annotations.SerializedName
// DTO
data class MangaResponsesDTO(
    @SerializedName("result") val result: String,
    @SerializedName("response") val response: String,
    @SerializedName("data") val data: List<MangaDTO>,
)
package com.example.mangary3.domain.model

import com.google.gson.annotations.SerializedName

data class MangaDetailResponse(
    @SerializedName("result") val result: String,
    @SerializedName("data") val data: MangaFromAPI
)
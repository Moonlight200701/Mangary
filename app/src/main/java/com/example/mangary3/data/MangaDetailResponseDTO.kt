package com.example.mangary3.data

import com.example.mangary3.data.remote.responses.MangaFromAPIDTO
import com.google.gson.annotations.SerializedName

data class MangaDetailResponseDTO(
    @SerializedName("result") val result: String,
    @SerializedName("data") val data: MangaFromAPIDTO
)
package com.example.mangary3.data.remote.dto

import com.google.gson.annotations.SerializedName

data class MangaAttributesDTO(
    @SerializedName("title") val title: Map<String, String>,
    val description: Map<String, String>
)
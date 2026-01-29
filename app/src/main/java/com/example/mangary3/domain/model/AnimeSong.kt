package com.example.mangary3.domain.model

import android.net.Uri

data class AnimeSong(
    val uri: Uri,
    val name: String,
    val type: MediaType
)

enum class MediaType{
    IMAGE,
    VIDEO,
    AUDIO
}
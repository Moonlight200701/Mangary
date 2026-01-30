package com.example.mangary3.core

import com.example.mangary3.data.remote.responses.MangaAttributesDTO
import com.example.mangary3.data.remote.responses.MangaFromAPIDTO
import com.example.mangary3.data.MangaAttributesDTO
import com.example.mangary3.data.MangaDetailResponseDTO
import com.example.mangary3.data.MangaFromAPIDTO

fun MangaFromAPIDTO.toModel() = MangaFromAPIDTO(
    id = id,
    type = type,
    attributes = attributes.toModel()

)

fun MangaAttributesDTO.toModel() = com.example.mangary3.data.MangaAttributesDTO(
    title = title,
    description = description
)

fun MangaDetailResponseDTO.toDTO()

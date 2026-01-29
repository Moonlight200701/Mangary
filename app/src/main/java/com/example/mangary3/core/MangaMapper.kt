package com.example.mangary3.core

import com.example.mangary3.data.remote.responses.MangaAttributesDTO
import com.example.mangary3.data.remote.responses.MangaFromAPIDTO
import com.example.mangary3.domain.model.MangaAttributes
import com.example.mangary3.domain.model.MangaFromAPI

fun MangaFromAPIDTO.toModel() = MangaFromAPI(
    id = id,
    type = type,
    attributes = attributes.toModel()

)

fun MangaAttributesDTO.toModel() = MangaAttributes(
    title = title,
    description = description
)

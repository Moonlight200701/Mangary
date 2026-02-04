package com.example.mangary3.core

import com.example.mangary3.data.remote.dto.MangaAttributesDTO
import com.example.mangary3.data.remote.dto.MangaDTO
import com.example.mangary3.data.remote.dto.MangaTagAttributesDTO
import com.example.mangary3.data.remote.dto.MangaTagDTO
import com.example.mangary3.domain.model.Manga
import com.example.mangary3.domain.model.MangaAttributes
import com.example.mangary3.domain.model.MangaTag
import com.example.mangary3.domain.model.MangaTagAttributes

fun MangaDTO.toModel(): Manga =
    Manga(
        id = id,
        type = type,
        attributes = attributes.toModel(),

    )


fun MangaAttributesDTO.toModel() = MangaAttributes(
    title = title,
    description = description
)

fun MangaTagAttributesDTO.toModel() = MangaTagAttributes(
    name = name
)

fun MangaTagDTO.toModel() = MangaTag(
    id = id,
    type = type,
    attributes = attributes.toModel()
)
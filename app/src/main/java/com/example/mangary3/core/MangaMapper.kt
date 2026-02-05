package com.example.mangary3.core

import com.example.mangary3.data.remote.dto.CoverAttributesDTO
import com.example.mangary3.data.remote.dto.MangaAttributesDTO
import com.example.mangary3.data.remote.dto.MangaDTO
import com.example.mangary3.data.remote.dto.MangaRelationshipDTO
import com.example.mangary3.data.remote.dto.MangaTagAttributesDTO
import com.example.mangary3.data.remote.dto.MangaTagDTO
import com.example.mangary3.domain.model.CoverAttributes
import com.example.mangary3.domain.model.Manga
import com.example.mangary3.domain.model.MangaAttributes
import com.example.mangary3.domain.model.MangaRelationship
import com.example.mangary3.domain.model.MangaTag
import com.example.mangary3.domain.model.MangaTagAttributes

fun MangaDTO.toModel(): Manga =
    Manga(
        id = id,
        type = type,
        attributes = attributes.toModel(),
        relationship = relationship.toModel()
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

fun List<MangaRelationshipDTO>.toModel(): List<MangaRelationship> = this.map { it.toModel() }

fun MangaRelationshipDTO.toModel() = MangaRelationship(
    type = this.type,
    attributes = this.attributes?.toModel()
)

fun CoverAttributesDTO?.toModel() = CoverAttributes(
    fileName = this?.fileName ?: ""
)
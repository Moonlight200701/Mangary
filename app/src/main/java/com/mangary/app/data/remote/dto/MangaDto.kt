package com.mangary.app.data.remote.dto

import com.google.gson.annotations.SerializedName

/**
 * Data Transfer Object for Manga response from MangaDex API
 */
data class MangaResponseDto(
    @SerializedName("result")
    val result: String,
    @SerializedName("response")
    val response: String,
    @SerializedName("data")
    val data: List<MangaDataDto>,
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("total")
    val total: Int
)

data class MangaDataDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("attributes")
    val attributes: MangaAttributesDto,
    @SerializedName("relationships")
    val relationships: List<RelationshipDto>
)

data class MangaAttributesDto(
    @SerializedName("title")
    val title: Map<String, String>,
    @SerializedName("altTitles")
    val altTitles: List<Map<String, String>>?,
    @SerializedName("description")
    val description: Map<String, String>?,
    @SerializedName("isLocked")
    val isLocked: Boolean?,
    @SerializedName("links")
    val links: Map<String, String>?,
    @SerializedName("originalLanguage")
    val originalLanguage: String?,
    @SerializedName("lastVolume")
    val lastVolume: String?,
    @SerializedName("lastChapter")
    val lastChapter: String?,
    @SerializedName("publicationDemographic")
    val publicationDemographic: String?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("year")
    val year: Int?,
    @SerializedName("contentRating")
    val contentRating: String?,
    @SerializedName("tags")
    val tags: List<TagDto>?,
    @SerializedName("state")
    val state: String?,
    @SerializedName("createdAt")
    val createdAt: String?,
    @SerializedName("updatedAt")
    val updatedAt: String?
)

data class TagDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("attributes")
    val attributes: TagAttributesDto
)

data class TagAttributesDto(
    @SerializedName("name")
    val name: Map<String, String>,
    @SerializedName("description")
    val description: Map<String, String>?,
    @SerializedName("group")
    val group: String?,
    @SerializedName("version")
    val version: Int?
)

data class RelationshipDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("related")
    val related: String?,
    @SerializedName("attributes")
    val attributes: Map<String, Any>?
)

package com.mangary.app.data.repository

import com.mangary.app.data.remote.api.MangaDexApiService
import com.mangary.app.data.remote.dto.MangaDataDto
import com.mangary.app.domain.model.Manga
import com.mangary.app.domain.model.Result
import com.mangary.app.domain.repository.MangaRepository
import javax.inject.Inject

/**
 * Implementation of MangaRepository
 * Part of data layer - handles data operations and mapping
 */
class MangaRepositoryImpl @Inject constructor(
    private val apiService: MangaDexApiService
) : MangaRepository {
    
    override suspend fun getMangaList(limit: Int, offset: Int): Result<List<Manga>> {
        return try {
            val response = apiService.getMangaList(limit, offset)
            if (response.isSuccessful && response.body() != null) {
                val mangaList = response.body()!!.data.map { it.toDomainModel() }
                Result.Success(mangaList)
            } else {
                Result.Error("Failed to fetch manga list: ${response.message()}")
            }
        } catch (e: Exception) {
            Result.Error("Network error: ${e.message}", e)
        }
    }
    
    override suspend fun getMangaById(mangaId: String): Result<Manga> {
        return try {
            val response = apiService.getMangaById(mangaId)
            if (response.isSuccessful && response.body() != null) {
                val mangaData = response.body()!!.data.firstOrNull()
                if (mangaData != null) {
                    Result.Success(mangaData.toDomainModel())
                } else {
                    Result.Error("Manga not found")
                }
            } else {
                Result.Error("Failed to fetch manga: ${response.message()}")
            }
        } catch (e: Exception) {
            Result.Error("Network error: ${e.message}", e)
        }
    }
    
    override suspend fun searchManga(query: String, limit: Int, offset: Int): Result<List<Manga>> {
        return try {
            val response = apiService.searchManga(query, limit, offset)
            if (response.isSuccessful && response.body() != null) {
                val mangaList = response.body()!!.data.map { it.toDomainModel() }
                Result.Success(mangaList)
            } else {
                Result.Error("Failed to search manga: ${response.message()}")
            }
        } catch (e: Exception) {
            Result.Error("Network error: ${e.message}", e)
        }
    }
    
    /**
     * Extension function to map DTO to domain model
     */
    private fun MangaDataDto.toDomainModel(): Manga {
        val coverArtRelationship = relationships.find { it.type == "cover_art" }
        val coverFilename = coverArtRelationship?.attributes?.get("fileName") as? String
        val coverImageUrl = if (coverFilename != null) {
            "https://uploads.mangadex.org/covers/$id/$coverFilename.256.jpg"
        } else {
            null
        }
        
        return Manga(
            id = id,
            title = attributes.title.values.firstOrNull() ?: "Unknown Title",
            description = attributes.description?.values?.firstOrNull() ?: "No description available",
            coverImageUrl = coverImageUrl,
            status = attributes.status ?: "unknown",
            contentRating = attributes.contentRating ?: "unknown",
            year = attributes.year,
            tags = attributes.tags?.mapNotNull { it.attributes.name.values.firstOrNull() } ?: emptyList(),
            author = relationships.find { it.type == "author" }?.id,
            artist = relationships.find { it.type == "artist" }?.id
        )
    }
}

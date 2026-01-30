package com.mangary.app.data.remote.api

import com.mangary.app.data.remote.dto.MangaResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Retrofit API interface for MangaDex API endpoints
 */
interface MangaDexApiService {
    
    /**
     * Get list of manga from MangaDex
     * @param limit Number of results to return (default: 20, max: 100)
     * @param offset Offset for pagination
     * @param title Filter by manga title
     * @param includedTags Filter by tags (UUID)
     * @param order Sort order
     */
    @GET("manga")
    suspend fun getMangaList(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0,
        @Query("title") title: String? = null,
        @Query("includedTags[]") includedTags: List<String>? = null,
        @Query("order[latestUploadedChapter]") order: String = "desc",
        @Query("includes[]") includes: List<String> = listOf("cover_art")
    ): Response<MangaResponseDto>
    
    /**
     * Get manga details by ID
     * @param mangaId UUID of the manga
     */
    @GET("manga/{id}")
    suspend fun getMangaById(
        @Path("id") mangaId: String,
        @Query("includes[]") includes: List<String> = listOf("cover_art", "author", "artist")
    ): Response<MangaResponseDto>
    
    /**
     * Search manga by title
     * @param title Search query
     * @param limit Number of results
     * @param offset Pagination offset
     */
    @GET("manga")
    suspend fun searchManga(
        @Query("title") title: String,
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0,
        @Query("includes[]") includes: List<String> = listOf("cover_art")
    ): Response<MangaResponseDto>
}

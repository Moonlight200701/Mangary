package com.mangary.app.domain.repository

import com.mangary.app.domain.model.Manga
import com.mangary.app.domain.model.Result

/**
 * Repository interface for manga data operations
 * Part of domain layer - defines contract for data access
 */
interface MangaRepository {
    
    /**
     * Get list of manga with pagination
     * @param limit Number of results to return
     * @param offset Offset for pagination
     * @return Result containing list of manga or error
     */
    suspend fun getMangaList(
        limit: Int = 20,
        offset: Int = 0
    ): Result<List<Manga>>
    
    /**
     * Get manga details by ID
     * @param mangaId UUID of the manga
     * @return Result containing manga details or error
     */
    suspend fun getMangaById(mangaId: String): Result<Manga>
    
    /**
     * Search manga by title
     * @param query Search query
     * @param limit Number of results
     * @param offset Pagination offset
     * @return Result containing list of manga or error
     */
    suspend fun searchManga(
        query: String,
        limit: Int = 20,
        offset: Int = 0
    ): Result<List<Manga>>
}

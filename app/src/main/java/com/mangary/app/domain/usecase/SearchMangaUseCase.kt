package com.mangary.app.domain.usecase

import com.mangary.app.domain.model.Manga
import com.mangary.app.domain.model.Result
import com.mangary.app.domain.repository.MangaRepository
import javax.inject.Inject

/**
 * Use case for searching manga by title
 */
class SearchMangaUseCase @Inject constructor(
    private val repository: MangaRepository
) {
    suspend operator fun invoke(
        query: String,
        limit: Int = 20,
        offset: Int = 0
    ): Result<List<Manga>> {
        return if (query.isBlank()) {
            Result.Error("Search query cannot be empty")
        } else {
            repository.searchManga(query, limit, offset)
        }
    }
}

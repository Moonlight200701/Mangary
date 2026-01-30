package com.mangary.app.domain.usecase

import com.mangary.app.domain.model.Manga
import com.mangary.app.domain.model.Result
import com.mangary.app.domain.repository.MangaRepository
import javax.inject.Inject

/**
 * Use case for getting list of manga
 * Follows single responsibility principle - handles one business logic operation
 */
class GetMangaListUseCase @Inject constructor(
    private val repository: MangaRepository
) {
    suspend operator fun invoke(
        limit: Int = 20,
        offset: Int = 0
    ): Result<List<Manga>> {
        return repository.getMangaList(limit, offset)
    }
}

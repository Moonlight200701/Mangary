package com.mangary.app.domain.usecase

import com.mangary.app.domain.model.Manga
import com.mangary.app.domain.model.Result
import com.mangary.app.domain.repository.MangaRepository
import javax.inject.Inject

/**
 * Use case for getting manga details by ID
 */
class GetMangaByIdUseCase @Inject constructor(
    private val repository: MangaRepository
) {
    suspend operator fun invoke(mangaId: String): Result<Manga> {
        return repository.getMangaById(mangaId)
    }
}

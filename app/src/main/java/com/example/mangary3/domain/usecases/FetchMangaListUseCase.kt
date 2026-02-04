package com.example.mangary3.domain.usecases

import com.example.mangary3.core.toModel
import com.example.mangary3.domain.model.Manga
import com.example.mangary3.domain.repository.GeneralMangaRepository
import timber.log.Timber
import javax.inject.Inject

class FetchMangaListUseCase @Inject constructor(
    private val generalMangaRepository: GeneralMangaRepository
) {
    suspend operator fun invoke(): List<Manga> {
        Timber.d("Manga size: ${generalMangaRepository.getMangas()}")
        val mangaList = generalMangaRepository.getMangas().mapNotNull {
            try {
                Timber.d("Mapping General MangaDTO to Manga")
                it.toModel()
            } catch (e: Exception) {
                Timber.e(e, "Failed to map MangaDTO: ${e.message}")
                null
            }
        }
        Timber.d("Manga size: $mangaList")
        return mangaList
    }
}
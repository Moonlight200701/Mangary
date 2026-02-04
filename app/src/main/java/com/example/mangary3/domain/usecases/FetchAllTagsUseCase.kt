package com.example.mangary3.domain.usecases

import com.example.mangary3.core.toModel
import com.example.mangary3.domain.model.MangaTag
import com.example.mangary3.domain.repository.GeneralMangaRepository
import jakarta.inject.Inject

class FetchAllTagsUseCase @Inject constructor(
    private val generalMangaRepository: GeneralMangaRepository
) {
    suspend operator fun invoke(): List<MangaTag> =
        generalMangaRepository.getAllTags().map { it.toModel() }
}
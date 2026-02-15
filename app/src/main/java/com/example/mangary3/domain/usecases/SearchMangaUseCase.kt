package com.example.mangary3.domain.usecases

import com.example.mangary3.domain.repository.SearchMangaRepository
import javax.inject.Inject

class SearchMangaUseCase @Inject constructor(
    private val searchMangaRepository: SearchMangaRepository
) {
    suspend operator fun invoke(title: String) = searchMangaRepository.searchManga(title)

}
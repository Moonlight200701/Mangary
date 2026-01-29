package com.example.mangary3.domain.usecases

import com.example.mangary3.data.remote.responses.MangaFromAPIDTO
import com.example.mangary3.domain.repository.GeneralMangaRepository
import javax.inject.Inject

class FetchMangaListUseCase @Inject constructor(
    private val generalMangaRepository: GeneralMangaRepository
) {
    suspend operator fun invoke(): List<MangaFromAPIDTO> = generalMangaRepository.getMangas()
}
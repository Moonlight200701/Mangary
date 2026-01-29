package com.example.mangary3.domain.usecases

import com.example.mangary3.domain.repository.MediaReaderRepository
import javax.inject.Inject

class PlayAnimeMusicUseCase @Inject constructor(
    private val mediaReaderRepository: MediaReaderRepository

) {
    operator fun invoke() {
        mediaReaderRepository.getAllMediaFiles()
    
    }
}
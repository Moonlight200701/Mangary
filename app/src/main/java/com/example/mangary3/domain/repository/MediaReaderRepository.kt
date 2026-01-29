package com.example.mangary3.domain.repository

import com.example.mangary3.domain.model.AnimeSong

interface MediaReaderRepository {
    fun getAllMediaFiles(): List<AnimeSong>
}
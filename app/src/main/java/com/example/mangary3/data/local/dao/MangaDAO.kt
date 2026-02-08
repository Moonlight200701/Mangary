package com.example.mangary3.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.mangary3.core.constants.APIConstants
import com.example.mangary3.data.local.entities.manga.LocalManga
import kotlinx.coroutines.flow.Flow

@Dao
interface MangaDAO {
    @Query("SELECT * FROM ${APIConstants.MANGA_TABLE_NAME}")
    fun getMangaFromLocal(): Flow<List<LocalManga>>
}
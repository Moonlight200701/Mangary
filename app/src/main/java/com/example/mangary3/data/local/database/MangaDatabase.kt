package com.example.mangary3.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mangary3.data.local.dao.MangaDAO
import com.example.mangary3.data.local.entities.manga.LocalManga

@Database(entities = [LocalManga::class], version = 1)
abstract class MangaDatabase: RoomDatabase() {
    abstract fun mangaDAO(): MangaDAO
}
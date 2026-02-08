package com.example.mangary3.data.local.entities.manga

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mangary3.core.constants.APIConstants

@Entity(tableName = APIConstants.MANGA_TABLE_NAME)
data class LocalManga(
    @PrimaryKey val id: Int,
    @ColumnInfo val title: String,
//    @ColumnInfo val author: String,
//    @ColumnInfo val coverUrl: String,
//    @ColumnInfo val description: String,
//    @ColumnInfo val genres: List<String>,
)

//Dummy Data
val trendingMangaList = listOf(
    LocalManga(1, "Magic Emperor Chapter 220"),
    LocalManga(2, "Ranker Second Chapter 97"),
    LocalManga(3, "Martial Reigns Chapter 317"),
    LocalManga(4, "Versatile Maze Chapter 757")
)

val latestUpdatesList = listOf(
    LocalManga(5, "Urban Immortal Chapter 664"),
    LocalManga(6, "Dungeon Reset Chapter 88"),
    LocalManga(7, "Dao Yin Chapter 115")
)

//Dummy categories
val mangaCategories = listOf(
    "Action",
    "Love",
    "Adventure",
//    "Horror",
//    "Mystery",
//    "Comedy",
//    "School life",
//    "Drama",
//    "Fantasy",
//    "Sci-fi",
//    "Romance",
//    "Historical",
//    "Martial Arts",
//    "Mecha",
//    "Military",
//    "Police",
//    "Psychological",
//    "Super Power",
//    "Supernatural",
//    "Thriller",
    "Vampire",
    "Yaoi",
    "Yuri"
)
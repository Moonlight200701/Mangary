package com.example.mangary3.data.remote

import com.example.mangary3.data.MangaDetailResponseDTO
import com.example.mangary3.data.remote.dto.MangaResponsesDTO
import com.example.mangary3.data.remote.dto.TagResponseDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MangaAPIService {
    @GET("manga")
    suspend fun getMangasFromMangaDex(
        @Query("limit") limit: Int = 100,
        @Query("offset") offset: Int = 0,
        @Query("includes[]") includes: List<String> = listOf("cover_art")
    ): MangaResponsesDTO

    @GET("manga/{id}")
    suspend fun getMangaById(@Path("id") id: String): MangaDetailResponseDTO

//    @GET("manga")
//    suspend fun getMangasFromMangaDex(
//        @Query("limit") limit: Int = 10,
//        @Query("offset") offset: Int = 0,
//        @Query("includes[]") includes: List<String> = listOf("cover_art")
//    ): MangaResponsesDTO

    @GET("manga/tag")
    suspend fun getAllTags(): TagResponseDTO

    @GET("manga")
    suspend fun searchMangasFromMangaDex(
        @Query("title") title: String = "",
        @Query("limit") limit: Int = 100,
        @Query("offset") offset: Int = 0,
        @Query("includes[]") includes: List<String> = listOf("cover_art")
    ): MangaResponsesDTO

}
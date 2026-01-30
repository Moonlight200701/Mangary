package com.example.mangary3.data.remote

import com.example.mangary3.data.remote.responses.MangaResponses
import com.example.mangary3.data.MangaDetailResponseDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MangaAPIService {
    @GET("manga")
    suspend fun getMangasFromSource(
        @Query("limit") limit: Int = 10,
        @Query("offset") offset: Int = 0,
    ): MangaResponses

    @GET("manga/{id}")
    suspend fun getMangaById(@Path("id") id: String): MangaDetailResponseDTO

    @GET("manga")
    suspend fun getMangasFromSource(
        @Query("limit") limit: Int = 10,
        @Query("offset") offset: Int = 0,
        @Query("includes[]") includes: List<String> = listOf("cover_art")
    ): MangaResponses



}
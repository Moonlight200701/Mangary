package com.example.mangary3.di

import com.example.mangary3.core.Constants
import com.example.mangary3.data.remote.MangaAPIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MangaDatabaseModule {
    @Provides
    @Singleton
    fun provideMangaAPIService(): MangaAPIService{
        return Retrofit.Builder()
            .baseUrl(Constants.MANGA_DEX_WEB)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MangaAPIService::class.java)
    }
}
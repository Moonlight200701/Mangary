package com.example.mangary3.di

import com.example.mangary3.data.repository.GeneralMangaRepositoryImpl
import com.example.mangary3.domain.repository.GeneralMangaRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    //Provides Repository
    @Binds
    @Singleton
    abstract fun bindMangaNetworkRepository(
        mangaNetworkRepositoryImpl: GeneralMangaRepositoryImpl
    ): GeneralMangaRepository

}
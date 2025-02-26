package com.example.rickandmorty.repository

import com.example.rickandmorty.network.RickAndMortyCharactersApiService
import com.example.rickandmorty.repository.RickAndMortyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRickAndMortyRepository(
        apiService: RickAndMortyCharactersApiService
    ): RickAndMortyRepository = RickAndMortyRepository(apiService)
}
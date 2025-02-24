package com.example.rickandmorty.repository

import com.example.rickandmorty.network.RickAndMortyCharactersApiService

class RickAndMortyRepository(private val rickAndMortyCharactersApiService: RickAndMortyCharactersApiService) {
    suspend fun getCharacters(page: String) = rickAndMortyCharactersApiService.fetchCharacters(page)
}
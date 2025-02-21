package com.example.rickandmorty.repository

import com.example.rickandmorty.network.RickAndMortyCharactersApiService

class RickAndMortyRepository(private val rickAndMortyCharactersApiService: RickAndMortyCharactersApiService) {
    fun getCharacters(page: String) = rickAndMortyCharactersApiService.fetchCharacters(page)

}
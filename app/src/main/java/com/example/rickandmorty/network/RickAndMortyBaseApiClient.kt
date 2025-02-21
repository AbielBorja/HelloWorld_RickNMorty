package com.example.rickandmorty.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RickAndMortyBaseApiClient {

    private const val BASE_URL = "https://rickandmortyapi.com/api/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val rickAndMortyCharactersApiService: RickAndMortyCharactersApiService by lazy {
        retrofit.create(RickAndMortyCharactersApiService::class.java)
    }
}

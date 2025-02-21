    package com.example.rickandmorty.network

import retrofit2.converter.moshi.MoshiConverterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit

    object RickAndMortyBaseApiClient {

    private val  BASE_URL = "https://rickandmortyapi.com/api/"

    //Variable for moshi builder
    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    val rickAndMortyCharactersApiService: RickAndMortyCharactersApiService by lazy {
        retrofit.create(RickAndMortyCharactersApiService::class.java)
    }
}


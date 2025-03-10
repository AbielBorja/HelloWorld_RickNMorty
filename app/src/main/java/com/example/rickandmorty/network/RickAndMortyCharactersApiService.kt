package com.example.rickandmorty.network

import com.example.rickandmorty.model.CharacterResponse
import com.example.rickandmorty.model.Episode
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyCharactersApiService {
    @GET("character")
    suspend fun fetchCharacters(@Query("page") page: String): CharacterResponse

    @GET("episode/{ids}")
    suspend fun getEpisodes(@Path("ids") ids: String): List<Episode>
}

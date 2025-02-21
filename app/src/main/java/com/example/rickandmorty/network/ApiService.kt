package com.example.rickandmorty.network

import com.example.rickandmorty.model.CharacterResponse
import com.example.rickandmorty.model.Episode
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("character")
    fun fetchCharacters(@Query("page") page: String): Call<CharacterResponse>

    @GET("episode/{ids}")
    fun getEpisodes(@Path("ids") ids: String): Call<List<Episode>>
}

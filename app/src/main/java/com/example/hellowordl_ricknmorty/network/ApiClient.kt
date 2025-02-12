package com.example.hellowordl_ricknmorty.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object ApiClient {
    // The base of the url of the api
    private val BASE_URL = "https://rickandmortyapi.com/api/"
    // The variable for moshi builder adding the converter
    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    // We create an instance of retrofit by lazy so it can be initialized only when it is needed
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }
    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}
// Below the object we create an interface to define how Retrofit talks to the service using Get Method
interface ApiService{
    @GET("character")
    fun fetchCharacters(@Query("page")page:String): Call<CharacterResponse>

}
package com.example.rickandmorty.model
import com.squareup.moshi.Json

data class CharacterResponse(
    @Json(name = "info") val pageInfo: PageInfo,
    @Json(name = "results") val result: List<Character>
)


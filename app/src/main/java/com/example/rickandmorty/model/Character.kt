package com.example.rickandmorty.model

import com.squareup.moshi.Json

data class Character(
    @Json(name = "name") val characterName: String,
    @Json(name = "image") val characterImage: String,
    @Json(name = "species") val species: String
)

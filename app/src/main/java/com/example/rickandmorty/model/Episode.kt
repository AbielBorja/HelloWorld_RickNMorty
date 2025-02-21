package com.example.rickandmorty.model

import com.squareup.moshi.Json

data class Episode(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "air_date") val air_date: String,
    @Json(name = "episode") val episode: String,
    @Json(name = "characters") val characters: List<String>,
    @Json(name = "url") val url: String,
    @Json(name = "created") val created: String
)

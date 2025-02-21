package com.example.rickandmorty.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Character(
    @Json(name = "name") val characterName: String,
    @Json(name = "image") val characterImage: String,
    @Json(name = "species") val species: String,
    @Json(name = "status") val status: String,
    @Json(name = "gender") val gender: String,
    @Json(name = "origin") val origin: Origin,
    @Json(name = "location") val location: Location,
    @Json(name = "episode") val episodes: List<String>
) : Parcelable

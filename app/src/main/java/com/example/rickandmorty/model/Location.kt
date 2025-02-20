package com.example.rickandmorty.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import com.squareup.moshi.Json

@Parcelize
data class Location(
    @Json(name = "name") val name: String,
    @Json(name = "url") val url: String
) : Parcelable
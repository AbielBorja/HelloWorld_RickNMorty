package com.example.rickandmorty.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Character(
    @SerializedName("name") val characterName: String,
    @SerializedName("image") val characterImage: String,
    @SerializedName("species") val species: String,
    @SerializedName("status") val status: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("origin") val origin: Origin,
    @SerializedName("location") val location: Location,
    @SerializedName("episode") val episodes: List<String>
) : Parcelable
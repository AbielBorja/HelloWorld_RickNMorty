package com.example.rickandmorty.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import com.google.gson.annotations.SerializedName

@Parcelize
data class Location(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
) : Parcelable

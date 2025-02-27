package com.example.rickandmorty.model

import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("info") val pageInfo: PageInfo,
    @SerializedName("results") val result: List<Character>
)
package com.mirhack.rickandmorty.data.model


import com.google.gson.annotations.SerializedName

data class CharactersResponse(
    @SerializedName("info")
    val info: CharactersResponseInfo,
    @SerializedName("results")
    val results: List<Character>
)
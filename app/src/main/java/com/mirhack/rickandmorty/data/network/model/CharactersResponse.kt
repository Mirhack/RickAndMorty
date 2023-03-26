package com.mirhack.rickandmorty.data.network.model


import com.google.gson.annotations.SerializedName

data class CharactersResponse(
    @SerializedName("info")
    val info: CharactersResponseInfoDTO,
    @SerializedName("results")
    val results: List<CharacterDTO>
)
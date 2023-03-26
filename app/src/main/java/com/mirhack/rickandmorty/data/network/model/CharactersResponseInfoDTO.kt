package com.mirhack.rickandmorty.data.network.model


import com.google.gson.annotations.SerializedName

data class CharactersResponseInfoDTO(
    @SerializedName("count")
    val count: Int,
    @SerializedName("pages")
    val pages: Int,
    @SerializedName("next")
    val next: String?,
    @SerializedName("prev")
    val prev: String?
)
package com.mirhack.rickandmorty.data.network.model


import com.google.gson.annotations.SerializedName

data class LocationLinkDTO(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)
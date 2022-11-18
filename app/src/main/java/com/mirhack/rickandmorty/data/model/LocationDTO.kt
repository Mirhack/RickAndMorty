package com.mirhack.rickandmorty.data.model


import com.google.gson.annotations.SerializedName

data class LocationDTO(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)
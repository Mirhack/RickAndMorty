package com.mirhack.rickandmorty.domain.model


data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: LocationLink,
    val lastKnownLocation: LocationLink,
    val image: String,
    val episodes: List<Int>,
)
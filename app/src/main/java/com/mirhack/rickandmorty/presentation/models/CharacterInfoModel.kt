package com.mirhack.rickandmorty.presentation.models

import com.mirhack.rickandmorty.domain.model.Episode
import com.mirhack.rickandmorty.domain.model.Location
import com.mirhack.rickandmorty.domain.model.Origin


data class CharacterInfoModel(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: Origin,
    val location: Location,
    val image: String,
    val episodes: List<Episode>
)
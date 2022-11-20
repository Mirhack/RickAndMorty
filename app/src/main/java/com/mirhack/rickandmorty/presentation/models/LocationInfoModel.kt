package com.mirhack.rickandmorty.presentation.models

import com.mirhack.rickandmorty.domain.model.Character


data class LocationInfoModel(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<Character>,
)
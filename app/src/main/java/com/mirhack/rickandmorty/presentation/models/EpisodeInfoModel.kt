package com.mirhack.rickandmorty.presentation.models

import com.mirhack.rickandmorty.domain.model.Character


data class EpisodeInfoModel(
    val id: Int,
    val name: String,
    val airDate: String,
    val code: String,
    val characters: List<Character>,
)
package com.mirhack.rickandmorty.presentation.mapper

import com.mirhack.rickandmorty.domain.model.Character
import com.mirhack.rickandmorty.domain.model.Episode
import com.mirhack.rickandmorty.presentation.models.CharacterInfoModel

fun Character.toCharacterInfo(episodes: List<Episode>) =
    CharacterInfoModel(
        id = id,
        name = name,
        status = status,
        species = species,
        type = type,
        gender = gender,
        origin = origin,
        location = location,
        image = image,
        episodes = episodes
    )
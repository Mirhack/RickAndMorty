package com.mirhack.rickandmorty.presentation.mapper

import com.mirhack.rickandmorty.domain.model.Character
import com.mirhack.rickandmorty.domain.model.Episode
import com.mirhack.rickandmorty.presentation.models.CharacterInfoModel
import com.mirhack.rickandmorty.presentation.models.EpisodeInfoModel

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

fun Episode.toEpisodeInfo(characters: List<Character>) =
    EpisodeInfoModel(
        id = id,
        name = name,
        airDate = airDate,
        code = code,
        characters = characters
    )
package com.mirhack.rickandmorty.data.mapper

import com.mirhack.rickandmorty.data.model.CharacterDTO
import com.mirhack.rickandmorty.data.model.EpisodeDTO
import com.mirhack.rickandmorty.data.model.LocationDTO
import com.mirhack.rickandmorty.data.model.OriginDTO
import com.mirhack.rickandmorty.domain.model.Character
import com.mirhack.rickandmorty.domain.model.Episode
import com.mirhack.rickandmorty.domain.model.Location
import com.mirhack.rickandmorty.domain.model.Origin

fun List<CharacterDTO>.toDomainCharacters() =
    map(CharacterDTO::toDomain)

fun CharacterDTO.toDomain() =
    Character(
        id = id,
        name = name,
        status = status,
        species = species,
        type = type,
        gender = gender,
        origin = origin.toDomain(),
        location = location.toDomain(),
        image = image,
        episodes = episode.mapNotNull(String::getId),
    )

fun OriginDTO.toDomain() =
    Origin(name, url.getId())

fun LocationDTO.toDomain() =
    Location(name, url.getId())

fun List<EpisodeDTO>.toDomain() =
    map(EpisodeDTO::toDomain)

fun EpisodeDTO.toDomain() =
    Episode(
        id = id,
        name = name,
        airDate = airDate,
        code = episode,
        characters = characters.mapNotNull(String::getId)
    )

private fun String.getId() =
    takeIf { it.isNotEmpty() }?.substringAfterLast("/")?.toInt()
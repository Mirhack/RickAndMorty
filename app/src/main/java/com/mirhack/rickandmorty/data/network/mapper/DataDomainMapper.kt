package com.mirhack.rickandmorty.data.network.mapper

import com.mirhack.rickandmorty.data.network.model.CharacterDTO
import com.mirhack.rickandmorty.data.network.model.EpisodeDTO
import com.mirhack.rickandmorty.data.network.model.LocationDTO
import com.mirhack.rickandmorty.data.network.model.LocationLinkDTO
import com.mirhack.rickandmorty.domain.model.Character
import com.mirhack.rickandmorty.domain.model.Episode
import com.mirhack.rickandmorty.domain.model.Location
import com.mirhack.rickandmorty.domain.model.LocationLink

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

fun LocationLinkDTO.toDomain() =
    LocationLink(name, url.getId())

fun LocationDTO.toDomain() =
    Location(
        id = id,
        name = name,
        type = type,
        dimension = dimension,
        residents = residents.mapNotNull(String::getId)
    )

fun List<EpisodeDTO>.toDomainEpisodes() =
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
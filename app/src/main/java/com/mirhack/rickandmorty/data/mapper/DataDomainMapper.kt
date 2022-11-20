package com.mirhack.rickandmorty.data.mapper

import com.mirhack.rickandmorty.data.model.CharacterDTO
import com.mirhack.rickandmorty.data.model.EpisodeDTO
import com.mirhack.rickandmorty.data.model.LocationDTO
import com.mirhack.rickandmorty.data.model.LocationLinkDTO
import com.mirhack.rickandmorty.data.model.OriginDTO
import com.mirhack.rickandmorty.domain.model.Character
import com.mirhack.rickandmorty.domain.model.Episode
import com.mirhack.rickandmorty.domain.model.Location
import com.mirhack.rickandmorty.domain.model.LocationLink
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
package com.mirhack.rickandmorty.data.network.mapper

import com.mirhack.rickandmorty.data.db.model.CharacterEntity
import com.mirhack.rickandmorty.data.db.model.CharacterFull
import com.mirhack.rickandmorty.data.db.model.LocationLinkEntity
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

fun CharacterDTO.toEntity(): CharacterEntity =
    CharacterEntity(
        id = id,
        name = name,
        status = status,
        species = species,
        type = type,
        gender = gender,
        image = image,
        episode = episode,
        url = url,
        created = created
    )

fun CharacterFull.toDomain(): Character =
    Character(
        id = character.id,
        name = character.name,
        status = character.status,
        species = character.species,
        type = character.type,
        gender = character.gender,
        image = character.image,
        origin = origin?.toDomain() ?: LocationLink("", null),
        lastKnownLocation = location?.toDomain() ?: LocationLink("", null),
        episodes = character.episode.mapNotNull(String::getId)
    )

fun LocationLinkEntity.toDomain(): LocationLink {
    return LocationLink(name, url.getId())
}

fun LocationLinkDTO.toEntity(): LocationLinkEntity =
    LocationLinkEntity(
        name = name,
        url = url
    )


fun CharacterDTO.toDomain() =
    Character(
        id = id,
        name = name,
        status = status,
        species = species,
        type = type,
        gender = gender,
        origin = origin.toDomain(),
        lastKnownLocation = location.toDomain(),
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
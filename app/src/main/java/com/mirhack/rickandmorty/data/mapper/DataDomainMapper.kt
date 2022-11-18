package com.mirhack.rickandmorty.data.mapper

import com.mirhack.rickandmorty.data.model.CharacterDTO
import com.mirhack.rickandmorty.data.model.LocationDTO
import com.mirhack.rickandmorty.data.model.OriginDTO
import com.mirhack.rickandmorty.domain.model.Character
import com.mirhack.rickandmorty.domain.model.Location
import com.mirhack.rickandmorty.domain.model.Origin

fun List<CharacterDTO>.toDomain() =
    map { it.toDomain() }

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
        episode = episode,
        url = url,
        created = created
    )

fun OriginDTO.toDomain() =
    Origin(name, url)

fun LocationDTO.toDomain() =
    Location(name, url)
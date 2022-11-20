package com.mirhack.rickandmorty.domain

import com.mirhack.rickandmorty.domain.model.Character
import com.mirhack.rickandmorty.domain.model.Episode
import com.mirhack.rickandmorty.domain.model.Location

interface Repository {

    suspend fun getCharacter(id: Int): Character

    suspend fun getCharacters(ids: List<Int>): List<Character>

    suspend fun getEpisode(id: Int): Episode

    suspend fun getEpisodes(ids: List<Int>): List<Episode>

    suspend fun getLocation(id: Int): Location
}

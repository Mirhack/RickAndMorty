package com.mirhack.rickandmorty.data

import com.mirhack.rickandmorty.data.mapper.toDomain
import com.mirhack.rickandmorty.domain.Repository
import com.mirhack.rickandmorty.domain.model.Character
import com.mirhack.rickandmorty.domain.model.Episode
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: RickAndMortyApiService
) : Repository {

    override suspend fun getCharacter(id: Int): Character =
        withContext(IO) { apiService.singleCharacter(id).toDomain() }

    override suspend fun getEpisode(id: Int): Episode =
        withContext(IO) { apiService.singleEpisode(id).toDomain() }

    override suspend fun getEpisodes(ids: List<Int>): List<Episode> =
        withContext(IO) { apiService.multipleEpisodes(ids).toDomain() }

}
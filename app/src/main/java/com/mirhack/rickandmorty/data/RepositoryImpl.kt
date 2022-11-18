package com.mirhack.rickandmorty.data

import com.mirhack.rickandmorty.data.mapper.toDomain
import com.mirhack.rickandmorty.domain.Repository
import com.mirhack.rickandmorty.domain.model.Character
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: RickAndMortyApiService
) : Repository {

    override suspend fun getCharacters(): List<Character> {
        return apiService.listCharacters().results.toDomain()
    }

}
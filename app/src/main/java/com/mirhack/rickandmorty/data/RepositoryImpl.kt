package com.mirhack.rickandmorty.data

import com.mirhack.rickandmorty.domain.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: RickAndMortyApiService
) : Repository {


}
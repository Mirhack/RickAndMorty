package com.mirhack.rickandmorty.data

import com.mirhack.rickandmorty.data.model.CharactersResponse
import retrofit2.http.GET

interface RickAndMortyApiService {

    @GET("api/character")
    suspend fun listCharacters(): CharactersResponse
}
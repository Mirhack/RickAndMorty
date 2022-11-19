package com.mirhack.rickandmorty.data

import com.mirhack.rickandmorty.data.model.CharacterDTO
import com.mirhack.rickandmorty.data.model.CharactersResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyApiService {

    @GET("api/character")
    suspend fun listCharacters(
        @Query("page") page: Int?
    ): CharactersResponse

    @GET("api/character/{id}")
    suspend fun singleCharacter(
        @Path("id") id: Int
    ): CharacterDTO
}
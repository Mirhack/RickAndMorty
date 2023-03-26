package com.mirhack.rickandmorty.data.network

import com.mirhack.rickandmorty.data.network.model.CharacterDTO
import com.mirhack.rickandmorty.data.network.model.CharactersResponse
import com.mirhack.rickandmorty.data.network.model.EpisodeDTO
import com.mirhack.rickandmorty.data.network.model.LocationDTO
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

    @GET("api/character/{ids}")
    suspend fun multipleCharacters(
        @Path("ids") id: List<Int>
    ): List<CharacterDTO>

    @GET("api/episode/{ids}")
    suspend fun multipleEpisodes(
        @Path("ids") id: List<Int>
    ): List<EpisodeDTO>

    @GET("api/episode/{id}")
    suspend fun singleEpisode(
        @Path("id") id: Int
    ): EpisodeDTO

    @GET("api/location/{id}")
    suspend fun singleLocation(
        @Path("id") id: Int
    ): LocationDTO
}
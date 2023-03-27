package com.mirhack.rickandmorty.data.mediators

import javax.inject.Inject
import com.mirhack.rickandmorty.data.db.Database
import com.mirhack.rickandmorty.data.db.localStorage.CharactersLocalStorage
import com.mirhack.rickandmorty.data.network.RickAndMortyApiService

class CharactersRemoteMediator @Inject constructor(
    database: Database,
    private val api: RickAndMortyApiService,
    private val charactersLocalStorage: CharactersLocalStorage,
) : DefaultRemoteMediator(database.remoteKeysDao()) {
    override val query: String = "api/character"

    override suspend fun loadData(loadKey: Int): Int? {
        val response = api.listCharacters(loadKey)
        charactersLocalStorage.save(response.results)

        return response.info.next?.substringAfter(SUBSTRING_PATH)?.toIntOrNull()
    }

    override fun onRefresh() {
        charactersLocalStorage.clearTable()
    }

    companion object {
        private const val SUBSTRING_PATH = "page="
    }
}
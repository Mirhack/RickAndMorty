package com.mirhack.rickandmorty.data.mediators

import javax.inject.Inject
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.mirhack.rickandmorty.data.db.localStorage.CharactersLocalStorage
import com.mirhack.rickandmorty.data.network.RickAndMortyApiService
import com.mirhack.rickandmorty.domain.model.Character

@OptIn(ExperimentalPagingApi::class)
class CharactersRemoteMediator @Inject constructor(
    private val api: RickAndMortyApiService,
    private val charactersLocalStorage: CharactersLocalStorage,
) : RemoteMediator<Int, Character>() {
    private var page = 1

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Character>,
    ): MediatorResult {
        return try {
            when (loadType) {
                LoadType.REFRESH -> {
                    page = 1
                    charactersLocalStorage.clearTable()
                    MediatorResult.Success(false)
                }
                LoadType.PREPEND -> {
                    MediatorResult.Success(false)
                }
                LoadType.APPEND -> {
                    val hasNextPage = onAppend(page, state.config.pageSize)
                    if (hasNextPage) page++
                    MediatorResult.Success(!hasNextPage)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            MediatorResult.Error(e)
        }
    }

    private suspend fun onAppend(page: Int, pageSize: Int): Boolean {
        val response = api.listCharacters(page)
        charactersLocalStorage.save(response.results)
        return response.info.next != null
    }

}
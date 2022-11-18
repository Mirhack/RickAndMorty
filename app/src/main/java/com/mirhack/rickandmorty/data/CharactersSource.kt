package com.mirhack.rickandmorty.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mirhack.rickandmorty.data.mapper.toDomain
import com.mirhack.rickandmorty.domain.model.Character
import javax.inject.Inject

class CharactersSource @Inject constructor(
    private val apiService: RickAndMortyApiService
) : PagingSource<Int, Character>() {

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        return try {
            val page = params.key ?: 1
            val charactersResponse = apiService.listCharacters(page = page)
            LoadResult.Page(
                data = charactersResponse.results.toDomain(),
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (charactersResponse.info.next == null) null else page + 1
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }
}
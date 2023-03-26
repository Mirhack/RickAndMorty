package com.mirhack.rickandmorty.data.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mirhack.rickandmorty.data.network.mapper.toDomainCharacters
import com.mirhack.rickandmorty.domain.model.Character
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class CharactersSource @Inject constructor(
    private val apiService: RickAndMortyApiService
) : PagingSource<Int, Character>() {

    private val _loadingPage = MutableStateFlow(-1)
    val loadingPage = _loadingPage.asStateFlow()
    private val _isLoadingError = MutableStateFlow(false)
    val isLoadingError = _isLoadingError.asStateFlow()

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        return try {
            val page = params.key ?: 1
            _isLoadingError.emit(false)
            _loadingPage.emit(page)
            val charactersResponse = apiService.listCharacters(page = page)
            LoadResult.Page(
                data = charactersResponse.results.toDomainCharacters(),
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (charactersResponse.info.next == null) null else page + 1
            )
        } catch (exception: Exception) {
            _isLoadingError.emit(true)
            return LoadResult.Error(exception)
        } finally {
            _loadingPage.emit(-1)
        }
    }
}
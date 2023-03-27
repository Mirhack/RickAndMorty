package com.mirhack.rickandmorty.presentation.screens.charactersList

import javax.inject.Inject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mirhack.rickandmorty.data.db.Database
import com.mirhack.rickandmorty.data.mediators.CharactersRemoteMediator
import com.mirhack.rickandmorty.data.network.CharactersSource
import com.mirhack.rickandmorty.data.network.mapper.toDomain
import com.mirhack.rickandmorty.domain.model.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

@HiltViewModel
class CharactersListViewModel @Inject constructor(
    private val source: CharactersSource,
    private val database: Database,
    private val charactersRemoteMediator: CharactersRemoteMediator,
) : ViewModel() {

    private val _viewModelState = MutableStateFlow(CharactersListState())
    val viewModelState: StateFlow<CharactersListState>
        get() = _viewModelState

    init {
        observeLoading()
        initListCharacters()
    }

    @OptIn(ExperimentalPagingApi::class)
    private fun initListCharacters() {
        val pagingSourceFactory =
            database.charactersDao()
                .getAll()
                .map { it.toDomain() }
                .asPagingSourceFactory()

        val characters: Flow<PagingData<Character>> =
            Pager(
                config = PagingConfig(
                    pageSize = 20,
                    initialLoadSize = 20
                ),
                remoteMediator = charactersRemoteMediator,
                pagingSourceFactory = pagingSourceFactory,
            ).flow.cachedIn(viewModelScope)

        _viewModelState.update { it.copy(characters = characters) }
    }

    private fun observeLoading() {
        source.loadingPage.onEach { loadingPage ->
            _viewModelState.update { it.copy(isLoading = loadingPage == 1) }
        }.launchIn(viewModelScope)

        source.isLoadingError.onEach { isError ->
            _viewModelState.update { it.copy(isLoadingError = isError) }
        }.launchIn(viewModelScope)
    }
}
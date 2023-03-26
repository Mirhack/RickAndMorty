package com.mirhack.rickandmorty.presentation.screens.charactersList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mirhack.rickandmorty.data.network.CharactersSource
import com.mirhack.rickandmorty.domain.model.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CharactersListViewModel @Inject constructor(
    private val source: CharactersSource
) : ViewModel() {

    private val _viewModelState = MutableStateFlow(CharactersListState())
    val viewModelState: StateFlow<CharactersListState>
        get() = _viewModelState

    init {
        observeLoading()
        initListCharacters()
    }

    private fun initListCharacters() {
        val characters: Flow<PagingData<Character>> = Pager(PagingConfig(pageSize = 20)) {
            source
        }.flow.cachedIn(viewModelScope)

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
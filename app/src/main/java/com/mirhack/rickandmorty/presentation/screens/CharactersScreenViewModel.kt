package com.mirhack.rickandmorty.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mirhack.rickandmorty.data.CharactersSource
import com.mirhack.rickandmorty.domain.model.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CharactersScreenViewModel @Inject constructor(
    private val source: CharactersSource
) : ViewModel() {

    private val _viewModelState = MutableStateFlow(CharactersState())
    val viewModelState: StateFlow<CharactersState>
        get() = _viewModelState

    init {
        initListCharacters()
    }

    private fun initListCharacters() {
        val characters: Flow<PagingData<Character>> = Pager(PagingConfig(pageSize = 20)) {
            source
        }.flow.cachedIn(viewModelScope)

        _viewModelState.update { it.copy(characters = characters) }
    }
}
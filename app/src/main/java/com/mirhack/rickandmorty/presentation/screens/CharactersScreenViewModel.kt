package com.mirhack.rickandmorty.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mirhack.rickandmorty.domain.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersScreenViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _viewModelState = MutableStateFlow(CharactersState())
    val viewModelState: StateFlow<CharactersState>
        get() = _viewModelState

    init {
        updateCharacters()
    }

    private fun updateCharacters() = viewModelScope.launch {
        repository.getCharacters().also {
                _viewModelState.value.characters.addAll(it)
        }
    }
}
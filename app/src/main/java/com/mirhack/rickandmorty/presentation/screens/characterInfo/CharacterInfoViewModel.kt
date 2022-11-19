package com.mirhack.rickandmorty.presentation.screens.characterInfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mirhack.rickandmorty.domain.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterInfoViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _viewModelState = MutableStateFlow(CharactersInfoState())
    val viewModelState: StateFlow<CharactersInfoState>
        get() = _viewModelState

    fun init(id: Int) {
        viewModelScope.launch {
            repository.getCharacter(id)
                .also { character -> _viewModelState.update { it.copy(character = character) } }
        }
    }
}
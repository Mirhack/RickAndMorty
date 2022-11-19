package com.mirhack.rickandmorty.presentation.screens.characterInfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mirhack.rickandmorty.domain.Repository
import com.mirhack.rickandmorty.presentation.mapper.toCharacterInfo
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
            val character = repository.getCharacter(id)
            val episodes = repository.getEpisodes(character.episodes)
            _viewModelState.update { it.copy(character = character.toCharacterInfo(episodes)) }
        }
    }

}
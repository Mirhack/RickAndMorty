package com.mirhack.rickandmorty.presentation.screens.episodeInfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mirhack.rickandmorty.domain.Repository
import com.mirhack.rickandmorty.presentation.mapper.toEpisodeInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodeInfoViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _viewModelState = MutableStateFlow(EpisodeInfoState())
    val viewModelState: StateFlow<EpisodeInfoState>
        get() = _viewModelState

    fun init(id: Int) {
        viewModelScope.launch {
            val episode = repository.getEpisode(id)
            val characters = repository.getCharacters(episode.characters)
            _viewModelState.update { it.copy(episode = episode.toEpisodeInfo(characters)) }
        }
    }

}
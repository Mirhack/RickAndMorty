package com.mirhack.rickandmorty.presentation.screens.locationInfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mirhack.rickandmorty.domain.Repository
import com.mirhack.rickandmorty.presentation.mapper.toCharacterInfo
import com.mirhack.rickandmorty.presentation.mapper.toLocationInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationInfoViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _viewModelState = MutableStateFlow(LocationInfoState())
    val viewModelState: StateFlow<LocationInfoState>
        get() = _viewModelState

    fun init(id: Int) {
        viewModelScope.launch {
            val location = repository.getLocation(id)
            val characters = repository.getCharacters(location.residents)
            _viewModelState.update { it.copy(location = location.toLocationInfo(characters)) }
        }
    }

}
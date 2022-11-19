package com.mirhack.rickandmorty.presentation.screens.charactersList

import androidx.paging.PagingData
import com.mirhack.rickandmorty.domain.model.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class CharactersListState(
    val isLoading: Boolean = true,
    val characters: Flow<PagingData<Character>> = emptyFlow()
)

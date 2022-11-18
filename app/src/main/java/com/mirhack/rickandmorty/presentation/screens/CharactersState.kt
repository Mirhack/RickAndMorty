package com.mirhack.rickandmorty.presentation.screens

import androidx.paging.PagingData
import com.mirhack.rickandmorty.domain.model.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class CharactersState(
    val characters: Flow<PagingData<Character>> = emptyFlow()
)

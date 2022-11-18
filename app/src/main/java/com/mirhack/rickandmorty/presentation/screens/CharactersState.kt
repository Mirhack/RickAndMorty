package com.mirhack.rickandmorty.presentation.screens

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.mirhack.rickandmorty.domain.model.Character

data class CharactersState(
    val characters: SnapshotStateList<Character> = mutableStateListOf()
)

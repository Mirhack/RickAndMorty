package com.mirhack.rickandmorty.presentation.screens.characterInfo

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun CharacterInfoScreen(id: Int?) {
    val viewModel = hiltViewModel<CharacterInfoViewModel>()
    LaunchedEffect(key1 = id) { id?.let { viewModel.init(it) } }
    val uiState by remember { viewModel.viewModelState }.collectAsState()

    Text(text = uiState.character.toString())
}

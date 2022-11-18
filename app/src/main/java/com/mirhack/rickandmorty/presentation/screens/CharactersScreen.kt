package com.mirhack.rickandmorty.presentation.screens

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CharactersScreen() {
    val viewModel = hiltViewModel<CharactersScreenViewModel>()
}
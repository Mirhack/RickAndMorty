package com.mirhack.rickandmorty.presentation.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CharactersScreen() {
    val viewModel = hiltViewModel<CharactersScreenViewModel>()
    val uiState by remember { viewModel.viewModelState }.collectAsState()

    LazyColumn(content = {
        items(uiState.characters) {
            Text(text = it.name)
        }
    })


}
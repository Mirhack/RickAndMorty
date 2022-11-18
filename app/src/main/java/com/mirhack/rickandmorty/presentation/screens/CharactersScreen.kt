package com.mirhack.rickandmorty.presentation.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.mirhack.rickandmorty.domain.model.Character

@Composable
fun CharactersScreen() {
    val viewModel = hiltViewModel<CharactersScreenViewModel>()
    val uiState by remember { viewModel.viewModelState }.collectAsState()
    val characterListItems: LazyPagingItems<Character> =
        uiState.characters.collectAsLazyPagingItems()

    LazyColumn(content = {
        items(characterListItems) {
            Text(text = it?.name.orEmpty())
        }
    })

}
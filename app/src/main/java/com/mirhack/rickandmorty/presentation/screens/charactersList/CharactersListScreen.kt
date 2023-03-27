package com.mirhack.rickandmorty.presentation.screens.charactersList

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.Size
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideLazyListPreloader
import com.mirhack.rickandmorty.domain.model.Character
import com.mirhack.rickandmorty.presentation.elements.CharacterCard
import com.mirhack.rickandmorty.presentation.elements.Error
import com.mirhack.rickandmorty.presentation.elements.Loader
import com.mirhack.rickandmorty.presentation.navigation.Routes


@Composable
fun CharactersListScreen(navController: NavHostController) {
    val viewModel = hiltViewModel<CharactersListViewModel>()
    val uiState by remember { viewModel.viewModelState }.collectAsState()
    val characterListItems: LazyPagingItems<Character> =
        uiState.characters.collectAsLazyPagingItems()

    when {
        uiState.isLoadingError -> Error { characterListItems.retry() }
        uiState.isLoading -> Loader()
        else -> Content(characterListItems, navController)
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun Content(
    characterListItems: LazyPagingItems<Character>,
    navController: NavHostController,
) {
    val state = rememberLazyListState()
    LazyColumn(state = state,
        content = {
            items(characterListItems) { character ->
                character?.let {
                    CharacterCard(it)
                    { id -> navController.navigate(Routes.CharacterInfoScreen("$id").route) }
                }
            }
        })

//    https://github.com/bumptech/glide/pull/4951
//    GlideLazyListPreloader(
//        state = state,
//        data = characterListItems.itemSnapshotList.items,
//        size = Size(150f, 150f),
//        numberOfItemsToPreload = 19,
//    ){ item, requestBuilder ->
//        requestBuilder.load(item.image)
//    }
}


package com.mirhack.rickandmorty.presentation.screens.charactersList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import com.mirhack.rickandmorty.R
import com.mirhack.rickandmorty.domain.model.Character
import com.mirhack.rickandmorty.presentation.elements.TextBlock
import com.mirhack.rickandmorty.presentation.elements.CharacterTitle
import com.mirhack.rickandmorty.presentation.navigation.Routes
import com.mirhack.rickandmorty.presentation.ui.theme.Shapes


@Composable
fun CharactersListScreen(navController: NavHostController) {
    val viewModel = hiltViewModel<CharactersListViewModel>()
    val uiState by remember { viewModel.viewModelState }.collectAsState()
    val characterListItems: LazyPagingItems<Character> =
        uiState.characters.collectAsLazyPagingItems()

    LazyColumn(content = {
        items(characterListItems) { character ->
            character?.let {
                CharacterCard(it)
                { id -> navController.navigate(Routes.CharacterInfoScreen.route + "/$id") }
            }
        }
    })
}

@Composable
private fun CharacterCard(character: Character, clickListener: (id: Int) -> Unit) {
    Card(
        shape = Shapes.medium,
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(16.dp, 8.dp)
            .clickable { clickListener(character.id) }
    ) {
        Row {
            Column {
                AsyncImage(
                    modifier = Modifier.fillMaxHeight(),
                    alignment = Alignment.CenterStart,
                    model = character.image,
                    contentDescription = null
                )
            }
            Column(Modifier.padding(16.dp, 8.dp, 8.dp, 8.dp)) {
                CharacterTitle(character, false,1)
                TextBlock(
                    title = stringResource(R.string.last_known_location),
                    description = character.location.name,
                    1
                )
            }
        }
    }
}


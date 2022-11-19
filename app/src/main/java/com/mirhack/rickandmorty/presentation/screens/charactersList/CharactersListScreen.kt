package com.mirhack.rickandmorty.presentation.screens.charactersList

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import com.mirhack.rickandmorty.R
import com.mirhack.rickandmorty.domain.model.Character
import com.mirhack.rickandmorty.presentation.navigation.Routes
import com.mirhack.rickandmorty.presentation.ui.theme.Mantis
import com.mirhack.rickandmorty.presentation.ui.theme.Mojo
import com.mirhack.rickandmorty.presentation.ui.theme.Shapes
import com.mirhack.rickandmorty.presentation.ui.theme.SilverChalice
import com.mirhack.rickandmorty.presentation.ui.theme.Typography

private const val ALIVE = "Alive"
private const val DEAD = "Dead"

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
                { id -> navController.navigate(Routes.CharacterInfoScreen.route+"/$id") }
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
                Text(
                    text = character.name,
                    style = Typography.h1,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    StatusCircle(character.status)
                    Text(
                        modifier = Modifier.padding(start = 8.dp),
                        text = "${character.status} - ${character.species}",
                        style = Typography.h3,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = stringResource(R.string.last_known_location),
                    style = Typography.subtitle1
                )
                Text(
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    text = character.location.name,
                    style = Typography.h2
                )
            }
        }
    }
}

@Composable
private fun StatusCircle(status: String) {
    val color = when (status) {
        ALIVE -> Mantis
        DEAD -> Mojo
        else -> SilverChalice
    }
    Canvas(modifier = Modifier.size(10.dp), onDraw = {
        drawCircle(color)
    })
}
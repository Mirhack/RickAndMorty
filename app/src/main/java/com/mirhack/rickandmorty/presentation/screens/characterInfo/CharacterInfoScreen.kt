package com.mirhack.rickandmorty.presentation.screens.characterInfo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Chip
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.mirhack.rickandmorty.R
import com.mirhack.rickandmorty.domain.model.Episode
import com.mirhack.rickandmorty.presentation.elements.CharacterTitle
import com.mirhack.rickandmorty.presentation.elements.ChipBlock
import com.mirhack.rickandmorty.presentation.elements.Loader
import com.mirhack.rickandmorty.presentation.elements.TextBlock
import com.mirhack.rickandmorty.presentation.navigation.Routes
import com.mirhack.rickandmorty.presentation.ui.theme.Shapes
import com.mirhack.rickandmorty.presentation.ui.theme.Typography


@Composable
fun CharacterInfoScreen(id: Int?, navController: NavController) {
    val viewModel = hiltViewModel<CharacterInfoViewModel>()
    LaunchedEffect(key1 = id) { id?.let { viewModel.init(it) } }
    val uiState by remember { viewModel.viewModelState }.collectAsState()

    if (uiState.character == null)
        Loader()
    else
        Content(uiState, navController)
}

@Composable
private fun Content(uiState: CharactersInfoState, navController: NavController) {
    Column(
        Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Red),
            contentScale = ContentScale.Crop,
            model = uiState.character?.image,
            contentDescription = null
        )

        uiState.character?.let { character ->
            Column(Modifier.padding(16.dp)) {
                CharacterTitle(
                    name = character.name,
                    type = character.type,
                    status = character.status,
                    species = character.species,
                    showType = true
                )
                TextBlock(
                    title = stringResource(R.string.gender),
                    description = character.gender,
                )
                LocationBlock(
                    locationId = character.location.id,
                    title = stringResource(R.string.last_known_location),
                    description = character.location.name,
                    navController = navController
                )
                LocationBlock(
                    locationId = character.origin.id,
                    title = stringResource(R.string.first_seen_in),
                    description = character.origin.name,
                    navController = navController
                )
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = stringResource(R.string.episodes),
                    style = Typography.subtitle1
                )
                LazyRow(Modifier.padding(top = 8.dp)) {
                    items(character.episodes) {
                        EpisodeChip(it) { id -> navController.navigate(Routes.EpisodeInfoScreen("$id").route) }
                    }
                }
            }
        }
    }
}

@Composable
private fun LocationBlock(
    locationId: Int?,
    title: String,
    description: String,
    navController: NavController
) {
    if (locationId != null) {
        ChipBlock(title, description) {
            navController.navigate(Routes.LocationInfoScreen("$locationId").route)
        }
    } else {
        TextBlock(title, description)
    }
}

@Composable
@OptIn(ExperimentalMaterialApi::class)
private fun EpisodeChip(episode: Episode, onClick: (id: Int) -> Unit) {
    Chip(
        modifier = Modifier.padding(end = 8.dp),
        shape = Shapes.medium,
        onClick = { onClick(episode.id) }) {
        Column(
            horizontalAlignment = CenterHorizontally
        ) {
            Text(text = episode.name)
            Text(text = episode.airDate)
            Text(text = episode.code)
        }
    }
}

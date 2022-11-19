package com.mirhack.rickandmorty.presentation.screens.episodeInfo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mirhack.rickandmorty.R
import com.mirhack.rickandmorty.presentation.elements.Loader
import com.mirhack.rickandmorty.presentation.elements.SMALL_CARD_SIZE
import com.mirhack.rickandmorty.presentation.elements.SmallCharacterCard
import com.mirhack.rickandmorty.presentation.elements.TextBlock
import com.mirhack.rickandmorty.presentation.models.EpisodeInfoModel
import com.mirhack.rickandmorty.presentation.navigation.Routes
import com.mirhack.rickandmorty.presentation.ui.theme.Typography


@Composable
fun EpisodeInfoScreen(id: Int?, navController: NavController) {
    val viewModel = hiltViewModel<EpisodeInfoViewModel>()
    LaunchedEffect(key1 = id) { id?.let { viewModel.init(it) } }
    val uiState by remember { viewModel.viewModelState }.collectAsState()

    if (uiState.episode == null)
        Loader()
    else
        Content(uiState, navController)
}

@Composable
private fun Content(uiState: EpisodeInfoState, navController: NavController) {
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        uiState.episode?.let { episode ->
            Column(Modifier.padding(16.dp)) {
                Text(
                    text = episode.name,
                    style = Typography.h1,
                )
                TextBlock(
                    title = stringResource(R.string.air_date),
                    description = episode.airDate
                )
                TextBlock(
                    title = stringResource(R.string.production_code),
                    description = episode.code
                )
                CharactersSection(episode, navController)
            }
        }
    }
}

@Composable
private fun CharactersSection(
    episode: EpisodeInfoModel,
    navController: NavController
) {
    Text(
        modifier = Modifier.padding(top = 8.dp),
        text = stringResource(R.string.characters),
        style = Typography.h2,
    )
    val rowsCount = 3
    LazyHorizontalGrid(
        rows = GridCells.Fixed(rowsCount),
        modifier = Modifier.height(rowsCount * SMALL_CARD_SIZE.dp)
    ) {
        items(episode.characters) {
            SmallCharacterCard(character = it, clickListener = { id ->
                navController.navigate(Routes.CharacterInfoScreen.route + "/$id")
            })
        }
    }
}


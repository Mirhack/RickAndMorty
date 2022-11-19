package com.mirhack.rickandmorty.presentation.screens.characterInfo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.mirhack.rickandmorty.R
import com.mirhack.rickandmorty.presentation.elements.CharacterTitle
import com.mirhack.rickandmorty.presentation.elements.Loader
import com.mirhack.rickandmorty.presentation.elements.TextBlock


@Composable
fun CharacterInfoScreen(id: Int?) {
    val viewModel = hiltViewModel<CharacterInfoViewModel>()
    LaunchedEffect(key1 = id) { id?.let { viewModel.init(it) } }
    val uiState by remember { viewModel.viewModelState }.collectAsState()

    if (uiState.character == null)
        Loader()
    else
        Content(uiState)
}

@Composable
private fun Content(uiState: CharactersInfoState) {
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
                TextBlock(
                    title = stringResource(R.string.last_known_location),
                    description = character.location.name,
                )
                TextBlock(
                    title = stringResource(R.string.first_seen_in),
                    description = character.origin.name,
                )
                TextBlock(
                    title = stringResource(R.string.episodes),
                    description = character.episodes.map { it.name }
                        .reduce { acc, s -> acc.plus("\n$s") },
                )
            }
        }

    }
}

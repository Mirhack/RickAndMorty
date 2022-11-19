package com.mirhack.rickandmorty.presentation.screens.episodeInfo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mirhack.rickandmorty.presentation.elements.Loader
import com.mirhack.rickandmorty.presentation.ui.theme.Typography


@Composable
fun EpisodeInfoScreen(id: Int?) {
    val viewModel = hiltViewModel<EpisodeInfoViewModel>()
    LaunchedEffect(key1 = id) { id?.let { viewModel.init(it) } }
    val uiState by remember { viewModel.viewModelState }.collectAsState()

    if (uiState.episode == null)
        Loader()
    else
        Content(uiState)
}

@Composable
private fun Content(uiState: EpisodeInfoState) {
    Column(
        Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        uiState.episode?.let { episode ->
            Column(Modifier.padding(16.dp)) {
                Text(
                    text = episode.name,
                    style = Typography.h1,
                )
            }
        }
    }
}


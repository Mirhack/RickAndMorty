package com.mirhack.rickandmorty.presentation.screens.locationInfo

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mirhack.rickandmorty.R
import com.mirhack.rickandmorty.presentation.elements.CharactersSection
import com.mirhack.rickandmorty.presentation.elements.Error
import com.mirhack.rickandmorty.presentation.elements.Loader
import com.mirhack.rickandmorty.presentation.elements.TextBlock
import com.mirhack.rickandmorty.presentation.navigation.Routes
import com.mirhack.rickandmorty.presentation.ui.theme.Typography


@Composable
fun LocationInfoScreen(id: Int?, navController: NavController) {
    val viewModel = hiltViewModel<LocationInfoViewModel>()
    LaunchedEffect(key1 = id) { id?.let { viewModel.init(it) } }
    val uiState by remember { viewModel.viewModelState }.collectAsState()

    when {
        uiState.isLoadingError -> Error { id?.let { viewModel.init(it) } }
        uiState.location == null -> Loader()
        else -> Content(uiState, navController)
    }
}

@Composable
private fun Content(uiState: LocationInfoState, navController: NavController) {
    Column(
        Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {

        uiState.location?.let { location ->
            Column(Modifier.padding(16.dp)) {
                Text(
                    text = location.name,
                    style = Typography.h1,
                )
                TextBlock(
                    title = stringResource(R.string.type),
                    description = location.type
                )
                TextBlock(
                    title = stringResource(R.string.dimension),
                    description = location.dimension
                )
                CharactersSection(stringResource(R.string.residents), location.residents) { id ->
                    navController.navigate(Routes.CharacterInfoScreen("$id").route)
                }
            }
        }
    }
}

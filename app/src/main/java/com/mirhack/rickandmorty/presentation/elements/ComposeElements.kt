package com.mirhack.rickandmorty.presentation.elements

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Chip
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import coil.compose.AsyncImage
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.mirhack.rickandmorty.R
import com.mirhack.rickandmorty.domain.model.Character
import com.mirhack.rickandmorty.presentation.ui.theme.Mantis
import com.mirhack.rickandmorty.presentation.ui.theme.Mojo
import com.mirhack.rickandmorty.presentation.ui.theme.Shapes
import com.mirhack.rickandmorty.presentation.ui.theme.SilverChalice
import com.mirhack.rickandmorty.presentation.ui.theme.Typography

const val SMALL_CARD_SIZE = 200
private const val ALIVE = "Alive"
private const val DEAD = "Dead"

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CharacterCard(character: Character, clickListener: (id: Int) -> Unit) {
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
                GlideImage(
                    modifier = Modifier.fillMaxHeight(),
                    contentScale = ContentScale.FillHeight,
                    alignment = Alignment.CenterStart,
                    model = character.image,
                    contentDescription = null
                ) {
                    it.thumbnail(
                        it.clone()
                            .load(character.image)
                            .override(150)
                    )
                }
            }
            Column(Modifier.padding(16.dp, 8.dp, 8.dp, 8.dp)) {
                CharacterTitle(
                    character.name,
                    character.type,
                    character.status,
                    character.species,
                    false,
                    1
                )
                TextBlock(
                    title = stringResource(R.string.last_known_location),
                    description = character.lastKnownLocation.name,
                    1
                )
            }
        }
    }
}

@Composable
fun SmallCharacterCard(character: Character, clickListener: (id: Int) -> Unit) {
    Card(
        shape = Shapes.medium,
        modifier = Modifier
            .padding(end = 16.dp, bottom = 16.dp)
            .size(SMALL_CARD_SIZE.dp)
            .clickable { clickListener(character.id) }
    ) {
        Box {
            AsyncImage(
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxWidth(),
                alignment = Alignment.TopCenter,
                model = character.image,
                contentDescription = null
            )
            Text(
                modifier = Modifier
                    .background(color = MaterialTheme.colors.surface)
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
                    .align(Alignment.BottomStart),
                text = character.name,
                style = Typography.h3,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun CharacterTitle(
    name: String,
    type: String,
    status: String,
    species: String,
    showType: Boolean,
    maxLines: Int = 999,
) {
    Text(
        text = name,
        style = Typography.h1,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis,
    )
    Row(verticalAlignment = Alignment.CenterVertically) {
        StatusCircle(status)
        val typePlaceholder = if (showType && type.isNotEmpty()) "- $type" else ""
        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = "$status - $species $typePlaceholder",
            style = Typography.h3,
            maxLines = maxLines,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
fun TextBlock(
    title: String,
    description: String,
    maxLines: Int = 999,
) {
    Text(
        modifier = Modifier.padding(top = 8.dp),
        text = title,
        style = Typography.subtitle1
    )
    Text(
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis,
        text = description,
        style = Typography.h4
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChipBlock(
    title: String,
    description: String,
    onClick: () -> Unit,
) {
    Text(
        modifier = Modifier.padding(top = 8.dp),
        text = title,
        style = Typography.subtitle1
    )
    Chip(
        modifier = Modifier.padding(end = 8.dp),
        shape = Shapes.medium, onClick = onClick
    ) {
        Text(
            text = description,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
fun CharactersSection(
    title: String,
    characters: List<Character>,
    onItemClick: (id: Int) -> Unit,
) {
    Text(
        modifier = Modifier.padding(top = 8.dp),
        text = title,
        style = Typography.h2,
    )
    val rowsCount = 3
    LazyHorizontalGrid(
        rows = GridCells.Fixed(rowsCount),
        modifier = Modifier.height(rowsCount * SMALL_CARD_SIZE.dp)
    ) {
        items(characters) {
            SmallCharacterCard(character = it, clickListener = onItemClick)
        }
    }
}

@Composable
fun StatusCircle(status: String) {
    val color = when (status) {
        ALIVE -> Mantis
        DEAD -> Mojo
        else -> SilverChalice
    }
    Canvas(modifier = Modifier.size(10.dp), onDraw = {
        drawCircle(color)
    })
}

@Composable
fun Loader() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
fun Error(onClick: () -> Unit) {
    Column(
        Modifier.fillMaxSize()
    ) {
        Box(modifier = Modifier.weight(0.5f, true)) {}
        Box(
            modifier = Modifier
                .weight(1.5f, true),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    modifier = Modifier
                        .padding(horizontal = 8.dp),
                    text = stringResource(R.string.error_line_1),
                    textAlign = TextAlign.Center,
                    style = Typography.h1
                )
                Text(
                    modifier = Modifier
                        .padding(top = 8.dp, bottom = 48.dp, start = 64.dp, end = 64.dp),
                    text = stringResource(R.string.error_line_2),
                    textAlign = TextAlign.Center,
                    style = Typography.h3
                )
                Button(onClick = onClick) {
                    Text(text = stringResource(R.string.retry))
                }
            }
        }

        val composition by rememberLottieComposition(LottieCompositionSpec.Asset("morty-cry-loader.json"))
        val progress by animateLottieCompositionAsState(composition)
        LottieAnimation(
            composition = composition,
            modifier = Modifier
                .weight(1.5f, true),
            alignment = Alignment.BottomCenter,
            progress = { progress },
        )
    }
}

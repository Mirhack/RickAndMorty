package com.mirhack.rickandmorty.presentation.elements

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.mirhack.rickandmorty.presentation.ui.theme.Mantis
import com.mirhack.rickandmorty.presentation.ui.theme.Mojo
import com.mirhack.rickandmorty.presentation.ui.theme.SilverChalice
import com.mirhack.rickandmorty.presentation.ui.theme.Typography

private const val ALIVE = "Alive"
private const val DEAD = "Dead"


@Composable
fun CharacterTitle(
    name: String,
    type: String,
    status: String,
    species: String,
    showType: Boolean,
    maxLines: Int = 999
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
    maxLines: Int = 999
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
        style = Typography.h2
    )
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

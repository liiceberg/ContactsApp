package ru.liiceberg.presentation.ui.components


import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage

@Composable
fun CircleAsyncImage(
    url: String,
    modifier: Modifier = Modifier,
    error: Painter? = null,
) {
    AsyncImage(
        model = url,
        contentDescription = null,
        modifier = modifier
            .clip(CircleShape)
            .aspectRatio(1f),
        contentScale = ContentScale.Crop,
        error = error,
    )
}

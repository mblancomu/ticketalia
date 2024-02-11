package com.manuelblanco.mobilechallenge.core.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter

/**
 * Created by Manuel Blanco Murillo on 9/2/24.
 */

@Composable
fun BoxScope.TicketsPoster(modifier: Modifier = Modifier, posterPath: String) {
    val painter = rememberAsyncImagePainter(
        model = posterPath,
        error = rememberVectorPainter(Icons.Filled.BrokenImage),
        placeholder = rememberVectorPainter(Icons.Default.Movie),
    )
    val colorFilter = when (painter.state) {
        is AsyncImagePainter.State.Loading, is AsyncImagePainter.State.Error -> ColorFilter.tint(
            MaterialTheme.colorScheme.background
        )

        else -> null
    }

    Image(
        painter = painter,
        colorFilter = colorFilter,
        contentDescription = "Image for Poster",
        contentScale = ContentScale.Crop,
        modifier = modifier,
    )
}
package com.manuelblanco.mobilechallenge.core.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.manuelblanco.mobilechallenge.core.ui.R

/**
 * Created by Manuel Blanco Murillo on 9/2/24.
 */

@Composable
fun BoxScope.TicketsPoster(
    modifier: Modifier = Modifier,
    posterPath: String,
    placeholder: Painter? = painterResource(id = R.drawable.placeholder)
) {
    val painter = rememberAsyncImagePainter(
        model = posterPath,
        error = placeholder,
        placeholder = placeholder,
    )
    val colorFilter = when (painter.state) {
        is AsyncImagePainter.State.Loading -> ColorFilter.tint(
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
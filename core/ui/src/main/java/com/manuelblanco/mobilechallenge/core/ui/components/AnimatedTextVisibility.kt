package com.manuelblanco.mobilechallenge.core.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.manuelblanco.mobilechallenge.core.designsystem.theme.TicketsTheme
import kotlinx.coroutines.delay

/**
 * Created by Manuel Blanco Murillo on 12/2/24.
 */

@Composable
fun AnimatedTextVisibility(
    info: String,
    icon: ImageVector
) {

    var state by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        delay(500)
        state = true
    }

    AnimatedVisibility(
        visible = state,
        enter = fadeIn(animationSpec = tween(2000)) + slideInHorizontally(),
        exit = fadeOut(animationSpec = tween(2000)) + slideOutHorizontally(),
    ) {
        Row {
            Icon(imageVector = icon, contentDescription = "Icon", tint = TicketsTheme.colors.secondary)
            Text(
                modifier = Modifier.padding(
                    start = TicketsTheme.dimensions.paddingMedium,
                    end = TicketsTheme.dimensions.paddingMedium
                ),
                text = info.ifBlank { "- -" }.toString(),
                textAlign = TextAlign.Start,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = TicketsTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Normal),
                color = TicketsTheme.colors.secondary,
            )
        }

    }
}
package com.manuelblanco.mobilechallenge.core.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.manuelblanco.mobilechallenge.core.designsystem.theme.TicketsTheme

/**
 * Created by Manuel Blanco Murillo on 12/2/24.
 */

@Composable
fun HeaderTitleDetail(
    title: String
) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = TicketsTheme.dimensions.paddingMedium,
                end = TicketsTheme.dimensions.paddingMedium,
                start = TicketsTheme.dimensions.paddingMedium
            ),
        text = title,
        textAlign = TextAlign.Center,
        style = TicketsTheme.typography.headlineSmall,
        color = TicketsTheme.colors.background,
        overflow = TextOverflow.Ellipsis,
        maxLines = 2
    )
}
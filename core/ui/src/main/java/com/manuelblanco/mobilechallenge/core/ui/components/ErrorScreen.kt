package com.manuelblanco.mobilechallenge.core.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.manuelblanco.mobilechallenge.core.designsystem.theme.TicketsTheme

/**
 * Created by Manuel Blanco Murillo on 16/2/24.
 */

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    isPaginatingError: Boolean,
    message: String) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (!isPaginatingError) {
            Icon(
                modifier = Modifier
                    .size(TicketsTheme.dimensions.bottomBarHeight),
                imageVector = Icons.Rounded.Warning, contentDescription = null
            )
        }

        Text(
            modifier = Modifier
                .padding(TicketsTheme.dimensions.paddingMedium),
            text = message,
            textAlign = TextAlign.Center,
        )
    }
}
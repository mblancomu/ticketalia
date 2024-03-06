package com.manuelblanco.mobilechallenge.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DoNotDisturb
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.manuelblanco.mobilechallenge.core.designsystem.theme.TicketsTheme

/**
 * Created by Manuel Blanco Murillo on 16/2/24.
 */

@Composable
fun EmptyListScreen(title: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(
            imageVector = Icons.Filled.DoNotDisturb,
            contentDescription = "Empty icon",
            modifier = Modifier.size(72.dp),
            tint = TicketsTheme.colors.primary
        )
        Text(
            modifier = Modifier
                .padding(TicketsTheme.dimensions.paddingMedium),
            text = title,
            style = TicketsTheme.typography.titleMedium,
            color = TicketsTheme.colors.primary
        )
    }
}

@Preview
@Composable
fun EmptyListScreenPreview() {
    TicketsTheme {
        EmptyListScreen(title = "No Events available", modifier = Modifier.fillMaxSize())
    }

}
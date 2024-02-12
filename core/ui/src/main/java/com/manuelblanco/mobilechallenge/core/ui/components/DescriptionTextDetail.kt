package com.manuelblanco.mobilechallenge.core.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.manuelblanco.mobilechallenge.core.designsystem.theme.TicketsTheme
import com.manuelblanco.mobilechallenge.core.ui.R

/**
 * Created by Manuel Blanco Murillo on 12/2/24.
 */

@Composable
fun DescriptionTextDetail(
    text: String
) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                bottom = TicketsTheme.dimensions.paddingMedium,
            ),
        text = text.ifEmpty { stringResource(id = R.string.fake_description) },
        textAlign = TextAlign.Justify,
        style = TicketsTheme.typography.bodyMedium,
        color = TicketsTheme.colors.secondaryContainer
    )
}
package com.manuelblanco.mobilechallenge.core.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.manuelblanco.mobilechallenge.core.designsystem.theme.TicketsTheme
import com.manuelblanco.mobilechallenge.core.ui.utils.borderRevealAnimation

/**
 * Created by Manuel Blanco Murillo on 12/2/24.
 */

@Composable
fun CircleInfoAnimated(
    info: String?,
    subtitle: String

) {
    Column(
        modifier = Modifier.padding(TicketsTheme.dimensions.paddingMedium),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.borderRevealAnimation()
        ) {
            Text(
                text = info?.ifBlank { "- -" }.toString(),
                textAlign = TextAlign.Center,
                style = TicketsTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                color = TicketsTheme.colors.primary,
                modifier = Modifier.align(alignment = Alignment.Center)
            )
        }

        CircleInfoSubtitle(subtitle = subtitle)
    }

}

@Composable
private fun CircleInfoSubtitle(
    subtitle: String
) {
    Text(
        text = subtitle,
        style = TicketsTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 8.dp),
        color = TicketsTheme.colors.surface
    )
}

@SuppressLint("UnusedBoxWithConstraintsScope")
@Preview
@Composable
fun CircleInfoAnimatedComponentPreview(
) {
    BoxWithConstraints {
        TicketsTheme {
            CircleInfoAnimated(info = "Test", subtitle = "Animation")
        }
    }
}
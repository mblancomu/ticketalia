package com.manuelblanco.mobilechallenge.core.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.manuelblanco.mobilechallenge.core.designsystem.theme.TicketsTheme

/**
 * Created by Manuel Blanco Murillo on 12/2/24.
 */

@Composable
fun NameItemList(name: String) = Text(
    text = name,
    style = TicketsTheme.typography.bodyMedium.copy(
        color = TicketsTheme.colors.surface,
        letterSpacing = 1.5.sp,
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.W500,
    ),
    maxLines = 1,
    overflow = TextOverflow.Ellipsis,
)

@Composable
fun FeatureItemList(icon: ImageVector, field: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = TicketsTheme.colors.surface,
            modifier = Modifier.size(16.dp)
        )
        Text(
            text = field,
            style = TicketsTheme.typography.bodySmall.copy(
                color = TicketsTheme.colors.surface,
                letterSpacing = 1.5.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.W400,
            ),
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            modifier = Modifier.padding(horizontal = 2.dp),
        )
    }
}

@SuppressLint("UnusedBoxWithConstraintsScope")
@Preview
@Composable
fun FeatureItemListComponentPreview(
) {
    BoxWithConstraints {
        TicketsTheme {
            FeatureItemList(icon = Icons.Default.Home, field = "Test field")
        }
    }
}
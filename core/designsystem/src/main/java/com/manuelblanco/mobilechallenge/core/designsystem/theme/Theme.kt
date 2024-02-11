package com.manuelblanco.mobilechallenge.core.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp

/**
 * Created by Manuel Blanco Murillo on 25/6/23.
 */

@Composable
fun TicketsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    colors: TicketsColors = TicketsTheme.colors,
    typography: TicketsTypography = TicketsTheme.typography,
    dimensions: TicketsDimensions = TicketsTheme.dimensions,
    content: @Composable () -> Unit
) {
    val rememberedColors =
        remember { colors.copy(isLight = darkTheme) }.apply { updateColorsFrom(colors) }

    val backgroundTheme = BackgroundTheme(
        color = colors.surface,
        tonalElevation = 2.dp,
    )

    val tintTheme = TintTheme()

    CompositionLocalProvider(
        LocalBackgroundTheme provides backgroundTheme,
        LocalTintTheme provides tintTheme,
        LocalColors provides rememberedColors,
        LocalDimensions provides dimensions,
        LocalTypography provides typography
    ) {
        content()
    }
}

object TicketsTheme {
    val colors: TicketsColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current
    val typography: TicketsTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
    val dimensions: TicketsDimensions
        @Composable
        @ReadOnlyComposable
        get() = LocalDimensions.current
}
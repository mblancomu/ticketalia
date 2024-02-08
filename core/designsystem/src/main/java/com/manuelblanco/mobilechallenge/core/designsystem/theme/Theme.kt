package com.manuelblanco.mobilechallenge.core.designsystem.theme

import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * Created by Manuel Blanco Murillo on 25/6/23.
 */

@VisibleForTesting
val LightColorScheme = lightColorScheme(
    primary = LightColorPrimary,
    onPrimary = LightColorOnPrimary,
    primaryContainer = LightColorPrimaryContainer,
    onPrimaryContainer = LightColorOnPrimaryContainer,
    secondary = LightColorSecondary,
    onSecondary = LightColorOnSecondary,
    secondaryContainer = LightColorSecondaryContainer,
    onSecondaryContainer = LightColorOnSecondaryContainer,
    tertiary = LightColorTertiary,
    onTertiary = LightColorOnTertiary,
    tertiaryContainer = LightColorTertiaryContainer,
    onTertiaryContainer = LightColorOnTertiaryContainer,
    error = LightColorError,
    onError = LightColorOnError,
    errorContainer = LightColorErrorContainer,
    onErrorContainer = LightColorOnErrorContainer,
    background = LightColorBackground,
    onBackground = LightColorOnBackground,
    surface = LightColorSurface,
    onSurface = LightColorOnSurface,
    surfaceVariant = LightColorSurfaceVariant,
    onSurfaceVariant = LightColorOnSurfaceVariant,
    inverseSurface = LightColorInverseSurface,
    inverseOnSurface = LightColorInverseOnSurface,
    outline = LightColorOutline,
    inversePrimary = LightColorInversePrimary,
    surfaceTint = LightColorSurfaceTint,
    outlineVariant = LightColorOutlineVariant,
    scrim = LightColorScrim,
)

@VisibleForTesting
val DarkColorScheme = darkColorScheme(
    primary = DarkColorPrimary,
    onPrimary = DarkColorOnPrimary,
    primaryContainer = DarkColorPrimaryContainer,
    onPrimaryContainer = DarkColorOnPrimaryContainer,
    secondary = DarkColorSecondary,
    onSecondary = DarkColorOnSecondary,
    secondaryContainer = DarkColorSecondaryContainer,
    onSecondaryContainer = DarkColorOnSecondaryContainer,
    tertiary = DarkColorTertiary,
    onTertiary = DarkColorOnTertiary,
    tertiaryContainer = DarkColorTertiaryContainer,
    onTertiaryContainer = DarkColorOnTertiaryContainer,
    error = DarkColorError,
    onError = DarkColorOnError,
    errorContainer = DarkColorErrorContainer,
    onErrorContainer = DarkColorOnErrorContainer,
    background = DarkColorBackground,
    onBackground = DarkColorOnBackground,
    surface = DarkColorSurface,
    onSurface = DarkColorOnSurface,
    surfaceVariant = DarkColorSurfaceVariant,
    onSurfaceVariant = DarkColorOnSurfaceVariant,
    inverseSurface = DarkColorInverseSurface,
    inverseOnSurface = DarkColorInverseOnSurface,
    outline = DarkColorOutline,
    inversePrimary = DarkColorInversePrimary,
    surfaceTint = DarkColorSurfaceTint,
    outlineVariant = DarkColorOutlineVariant,
    scrim = DarkColorScrim,
)

val DarkGradientColors = GradientColors(container = Color.Black)

val LightBackgroundTheme = BackgroundTheme(color = seed)

val DarkBackgroundTheme = BackgroundTheme(color = Color.Black)

@Composable
fun TicketsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    // Color scheme
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    // Background theme
    val backgroundTheme = BackgroundTheme(
        color = colorScheme.surface,
        tonalElevation = 2.dp,
    )

    val tintTheme = TintTheme()

    // Composition locals
    CompositionLocalProvider(
        LocalBackgroundTheme provides backgroundTheme,
        LocalTintTheme provides tintTheme,
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = ChallengeTypography,
            content = content,
        )
    }
}
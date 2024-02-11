package com.manuelblanco.mobilechallenge.core.designsystem.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.graphics.Color

/**
 * Created by Manuel Blanco Murillo on 25/6/23.
 */

class TicketsColors(
    primary: Color,
    onPrimary: Color,
    primaryContainer: Color,
    onPrimaryContainer: Color,
    inversePrimary: Color,
    secondary: Color,
    onSecondary: Color,
    secondaryContainer: Color,
    onSecondaryContainer: Color,
    tertiary: Color,
    onTertiary: Color,
    tertiaryContainer: Color,
    onTertiaryContainer: Color,
    background: Color,
    onBackground: Color,
    surface: Color,
    onSurface: Color,
    surfaceVariant: Color,
    onSurfaceVariant: Color,
    surfaceTint: Color,
    inverseSurface: Color,
    inverseOnSurface: Color,
    error: Color,
    onError: Color,
    errorContainer: Color,
    onErrorContainer: Color,
    outline: Color,
    outlineVariant: Color,
    scrim: Color,
    isLight: Boolean
) {
    var primary by mutableStateOf(primary, structuralEqualityPolicy())
        internal set
    var onPrimary by mutableStateOf(onPrimary, structuralEqualityPolicy())
        internal set
    var primaryContainer by mutableStateOf(primaryContainer, structuralEqualityPolicy())
        internal set
    var onPrimaryContainer by mutableStateOf(onPrimaryContainer, structuralEqualityPolicy())
        internal set
    var inversePrimary by mutableStateOf(inversePrimary, structuralEqualityPolicy())
        internal set
    var secondary by mutableStateOf(secondary, structuralEqualityPolicy())
        internal set
    var onSecondary by mutableStateOf(onSecondary, structuralEqualityPolicy())
        internal set
    var secondaryContainer by mutableStateOf(secondaryContainer, structuralEqualityPolicy())
        internal set
    var onSecondaryContainer by mutableStateOf(onSecondaryContainer, structuralEqualityPolicy())
        internal set
    var tertiary by mutableStateOf(tertiary, structuralEqualityPolicy())
        internal set
    var onTertiary by mutableStateOf(onTertiary, structuralEqualityPolicy())
        internal set
    var tertiaryContainer by mutableStateOf(tertiaryContainer, structuralEqualityPolicy())
        internal set
    var onTertiaryContainer by mutableStateOf(onTertiaryContainer, structuralEqualityPolicy())
        internal set
    var background by mutableStateOf(background, structuralEqualityPolicy())
        internal set
    var onBackground by mutableStateOf(onBackground, structuralEqualityPolicy())
        internal set
    var surface by mutableStateOf(surface, structuralEqualityPolicy())
        internal set
    var onSurface by mutableStateOf(onSurface, structuralEqualityPolicy())
        internal set
    var surfaceVariant by mutableStateOf(surfaceVariant, structuralEqualityPolicy())
        internal set
    var onSurfaceVariant by mutableStateOf(onSurfaceVariant, structuralEqualityPolicy())
        internal set
    var surfaceTint by mutableStateOf(surfaceTint, structuralEqualityPolicy())
        internal set
    var inverseSurface by mutableStateOf(inverseSurface, structuralEqualityPolicy())
        internal set
    var inverseOnSurface by mutableStateOf(inverseOnSurface, structuralEqualityPolicy())
        internal set
    var error by mutableStateOf(error, structuralEqualityPolicy())
        internal set
    var onError by mutableStateOf(onError, structuralEqualityPolicy())
        internal set
    var errorContainer by mutableStateOf(errorContainer, structuralEqualityPolicy())
        internal set
    var onErrorContainer by mutableStateOf(onErrorContainer, structuralEqualityPolicy())
        internal set
    var outline by mutableStateOf(outline, structuralEqualityPolicy())
        internal set
    var outlineVariant by mutableStateOf(outlineVariant, structuralEqualityPolicy())
        internal set
    var scrim by mutableStateOf(scrim, structuralEqualityPolicy())
        internal set

    var isLight by mutableStateOf(isLight)
        internal set

    fun copy(
        primary: Color = this.primary,
        onPrimary: Color = this.onPrimary,
        primaryContainer: Color = this.primaryContainer,
        onPrimaryContainer: Color = this.onPrimaryContainer,
        inversePrimary: Color = this.inversePrimary,
        secondary: Color = this.secondary,
        onSecondary: Color = this.onSecondary,
        secondaryContainer: Color = this.secondaryContainer,
        onSecondaryContainer: Color = this.onSecondaryContainer,
        tertiary: Color = this.tertiary,
        onTertiary: Color = this.onTertiary,
        tertiaryContainer: Color = this.tertiaryContainer,
        onTertiaryContainer: Color = this.onTertiaryContainer,
        background: Color = this.background,
        onBackground: Color = this.onBackground,
        surface: Color = this.surface,
        onSurface: Color = this.onSurface,
        surfaceVariant: Color = this.surfaceVariant,
        onSurfaceVariant: Color = this.onSurfaceVariant,
        surfaceTint: Color = this.surfaceTint,
        inverseSurface: Color = this.inverseSurface,
        inverseOnSurface: Color = this.inverseOnSurface,
        error: Color = this.error,
        onError: Color = this.onError,
        errorContainer: Color = this.errorContainer,
        onErrorContainer: Color = this.onErrorContainer,
        outline: Color = this.outline,
        outlineVariant: Color = this.outlineVariant,
        scrim: Color = this.scrim,
        isLight: Boolean = this.isLight
    ): TicketsColors =
        TicketsColors(
            primary = primary,
            onPrimary = onPrimary,
            primaryContainer = primaryContainer,
            onPrimaryContainer = onPrimaryContainer,
            inversePrimary = inversePrimary,
            secondary = secondary,
            onSecondary = onSecondary,
            secondaryContainer = secondaryContainer,
            onSecondaryContainer = onSecondaryContainer,
            tertiary = tertiary,
            onTertiary = onTertiary,
            tertiaryContainer = tertiaryContainer,
            onTertiaryContainer = onTertiaryContainer,
            background = background,
            onBackground = onBackground,
            surface = surface,
            onSurface = onSurface,
            surfaceVariant = surfaceVariant,
            onSurfaceVariant = onSurfaceVariant,
            surfaceTint = surfaceTint,
            inverseSurface = inverseSurface,
            inverseOnSurface = inverseOnSurface,
            error = error,
            onError = onError,
            errorContainer = errorContainer,
            onErrorContainer = onErrorContainer,
            outline = outline,
            outlineVariant = outlineVariant,
            scrim = scrim,
            isLight = isLight
        )

    fun updateColorsFrom(other: TicketsColors) {
        primary = other.primary
        onPrimary = other.onPrimary
        primaryContainer = other.primaryContainer
        onPrimaryContainer = other.onPrimaryContainer
        inversePrimary = other.inversePrimary
        secondary = other.secondary
        onSecondary = other.onSecondary
        secondaryContainer = other.secondaryContainer
        onSecondaryContainer = other.onSecondaryContainer
        tertiary = other.tertiary
        onTertiary = other.onTertiary
        tertiaryContainer = other.tertiaryContainer
        onTertiaryContainer = other.onTertiaryContainer
        background = other.background
        onBackground = other.onBackground
        surface = other.surface
        onSurface = other.onSurface
        surfaceVariant = other.surfaceVariant
        onSurfaceVariant = other.onSurfaceVariant
        surfaceTint = other.surfaceTint
        inverseSurface = other.inverseSurface
        inverseOnSurface = other.inverseOnSurface
        error = other.error
        onError = other.onError
        errorContainer = other.errorContainer
        onErrorContainer = other.onErrorContainer
        outline = other.outline
        outlineVariant = other.outlineVariant
        scrim = other.scrim
    }
}

internal val LightColorPrimary = Color(0xFF4651C8)
internal val LightColorOnPrimary = Color(0xFFFFFFFF)
internal val LightColorPrimaryContainer = Color(0xFFE0E0FF)
internal val LightColorOnPrimaryContainer = Color(0xFF000569)
internal val LightColorSecondary = Color(0xFFD6A728)
internal val LightColorOnSecondary = Color(0xFFFFFFFF)
internal val LightColorSecondaryContainer = Color(0xFFE1E0F9)
internal val LightColorOnSecondaryContainer = Color(0xFF191A2C)
internal val LightColorTertiary = Color(0xFF78536B)
internal val LightColorOnTertiary = Color(0xFFFFFFFF)
internal val LightColorTertiaryContainer = Color(0xFFFFD7EE)
internal val LightColorOnTertiaryContainer = Color(0xFF2E1126)
internal val LightColorError = Color(0xFFBA1A1A)
internal val LightColorErrorContainer = Color(0xFFFFDAD6)
internal val LightColorOnError = Color(0xFFFFFFFF)
internal val LightColorOnErrorContainer = Color(0xFF410002)
internal val LightColorBackground = Color(0xFFFFFBFF)
internal val LightColorOnBackground = Color(0xFF1B1B1F)
internal val LightColorSurface = Color(0xFFFFFBFF)
internal val LightColorOnSurface = Color(0xFF1B1B1F)
internal val LightColorSurfaceVariant = Color(0xFFE3E1EC)
internal val LightColorOnSurfaceVariant = Color(0xFF46464F)
internal val LightColorOutline = Color(0xFFD3D3D3)
internal val LightColorInverseOnSurface = Color(0xFFF3F0F4)
internal val LightColorInverseSurface = Color(0xFF303034)
internal val LightColorInversePrimary = Color(0xFFBEC2FF)
internal val LightColorShadow = Color(0xFF000000)
internal val LightColorSurfaceTint = Color(0xFF4651C8)
internal val LightColorOutlineVariant = Color(0xFFC7C5D0)
internal val LightColorScrim = Color(0xFF000000)

internal val DarkColorPrimary = Color(0xFFBEC2FF)
internal val DarkColorOnPrimary = Color(0xFF0B179A)
internal val DarkColorPrimaryContainer = Color(0xFF2B36AF)
internal val DarkColorOnPrimaryContainer = Color(0xFFE0E0FF)
internal val DarkColorSecondary = Color(0xFFC5C4DD)
internal val DarkColorOnSecondary = Color(0xFF2E2F42)
internal val DarkColorSecondaryContainer = Color(0xFF444559)
internal val DarkColorOnSecondaryContainer = Color(0xFFE1E0F9)
internal val DarkColorTertiary = Color(0xFFE7B9D5)
internal val DarkColorOnTertiary = Color(0xFF45263C)
internal val DarkColorTertiaryContainer = Color(0xFF5E3C53)
internal val DarkColorOnTertiaryContainer = Color(0xFFFFD7EE)
internal val DarkColorError = Color(0xFFFFB4AB)
internal val DarkColorErrorContainer = Color(0xFF93000A)
internal val DarkColorOnError = Color(0xFF690005)
internal val DarkColorOnErrorContainer = Color(0xFFFFDAD6)
internal val DarkColorBackground = Color(0xFF1B1B1F)
internal val DarkColorOnBackground = Color(0xFFE4E1E6)
internal val DarkColorSurface = Color(0xFF1B1B1F)
internal val DarkColorOnSurface = Color(0xFFE4E1E6)
internal val DarkColorSurfaceVariant = Color(0xFF46464F)
internal val DarkColorOnSurfaceVariant = Color(0xFFC7C5D0)
internal val DarkColorOutline = Color(0xFF91909A)
internal val DarkColorInverseOnSurface = Color(0xFF1B1B1F)
internal val DarkColorInverseSurface = Color(0xFFE4E1E6)
internal val DarkColorInversePrimary = Color(0xFF4651C8)
internal val DarkColorShadow = Color(0xFF000000)
internal val DarkColorSurfaceTint = Color(0xFFBEC2FF)
internal val DarkColorOutlineVariant = Color(0xFF46464F)
internal val DarkColorScrim = Color(0xFF000000)
internal val seed = Color(0xFF4651C8)

fun lightColorScheme(
    primary: Color = LightColorPrimary,
    onPrimary: Color = LightColorOnPrimary,
    primaryContainer: Color = LightColorPrimaryContainer,
    onPrimaryContainer: Color = LightColorOnPrimaryContainer,
    inversePrimary: Color = LightColorInversePrimary,
    secondary: Color = LightColorSecondary,
    onSecondary: Color = LightColorOnSecondary,
    secondaryContainer: Color = LightColorSecondaryContainer,
    onSecondaryContainer: Color = LightColorOnSecondaryContainer,
    tertiary: Color = LightColorTertiary,
    onTertiary: Color = LightColorOnTertiary,
    tertiaryContainer: Color = LightColorTertiaryContainer,
    onTertiaryContainer: Color = LightColorOnTertiaryContainer,
    background: Color = LightColorBackground,
    onBackground: Color = LightColorOnBackground,
    surface: Color = LightColorSurface,
    onSurface: Color = LightColorOnSurface,
    surfaceVariant: Color = LightColorSurfaceVariant,
    onSurfaceVariant: Color = LightColorOnSurfaceVariant,
    surfaceTint: Color = LightColorSurfaceTint,
    inverseSurface: Color = LightColorInverseSurface,
    inverseOnSurface: Color = LightColorInverseOnSurface,
    error: Color = LightColorError,
    onError: Color = LightColorOnError,
    errorContainer: Color = LightColorErrorContainer,
    onErrorContainer: Color = LightColorOnErrorContainer,
    outline: Color = LightColorOutline,
    outlineVariant: Color = LightColorOutlineVariant,
    scrim: Color = LightColorScrim,
): TicketsColors =
    TicketsColors(
        primary = primary,
        onPrimary = onPrimary,
        primaryContainer = primaryContainer,
        onPrimaryContainer = onPrimaryContainer,
        inversePrimary = inversePrimary,
        secondary = secondary,
        onSecondary = onSecondary,
        secondaryContainer = secondaryContainer,
        onSecondaryContainer = onSecondaryContainer,
        tertiary = tertiary,
        onTertiary = onTertiary,
        tertiaryContainer = tertiaryContainer,
        onTertiaryContainer = onTertiaryContainer,
        background = background,
        onBackground = onBackground,
        surface = surface,
        onSurface = onSurface,
        surfaceVariant = surfaceVariant,
        onSurfaceVariant = onSurfaceVariant,
        surfaceTint = surfaceTint,
        inverseSurface = inverseSurface,
        inverseOnSurface = inverseOnSurface,
        error = error,
        onError = onError,
        errorContainer = errorContainer,
        onErrorContainer = onErrorContainer,
        outline = outline,
        outlineVariant = outlineVariant,
        scrim = scrim,
        isLight = true
    )

/**
 * Returns a dark Material color scheme.
 */
fun darkColorScheme(
    primary: Color = DarkColorPrimary,
    onPrimary: Color = DarkColorOnPrimary,
    primaryContainer: Color = DarkColorPrimaryContainer,
    onPrimaryContainer: Color = DarkColorOnPrimaryContainer,
    inversePrimary: Color = DarkColorInversePrimary,
    secondary: Color = DarkColorSecondary,
    onSecondary: Color = DarkColorOnSecondary,
    secondaryContainer: Color = DarkColorSecondaryContainer,
    onSecondaryContainer: Color = DarkColorOnSecondaryContainer,
    tertiary: Color = DarkColorTertiary,
    onTertiary: Color = DarkColorOnTertiary,
    tertiaryContainer: Color = DarkColorTertiaryContainer,
    onTertiaryContainer: Color = DarkColorOnTertiaryContainer,
    background: Color = DarkColorBackground,
    onBackground: Color = DarkColorOnBackground,
    surface: Color = DarkColorSurface,
    onSurface: Color = DarkColorOnSurface,
    surfaceVariant: Color = DarkColorSurfaceVariant,
    onSurfaceVariant: Color = DarkColorOnSurfaceVariant,
    surfaceTint: Color = DarkColorSurfaceTint,
    inverseSurface: Color = DarkColorInverseSurface,
    inverseOnSurface: Color = DarkColorInverseOnSurface,
    error: Color = DarkColorError,
    onError: Color = DarkColorOnError,
    errorContainer: Color = DarkColorErrorContainer,
    onErrorContainer: Color = DarkColorOnErrorContainer,
    outline: Color = DarkColorOutline,
    outlineVariant: Color = DarkColorOutlineVariant,
    scrim: Color = DarkColorScrim,
): TicketsColors =
    TicketsColors(
        primary = primary,
        onPrimary = onPrimary,
        primaryContainer = primaryContainer,
        onPrimaryContainer = onPrimaryContainer,
        inversePrimary = inversePrimary,
        secondary = secondary,
        onSecondary = onSecondary,
        secondaryContainer = secondaryContainer,
        onSecondaryContainer = onSecondaryContainer,
        tertiary = tertiary,
        onTertiary = onTertiary,
        tertiaryContainer = tertiaryContainer,
        onTertiaryContainer = onTertiaryContainer,
        background = background,
        onBackground = onBackground,
        surface = surface,
        onSurface = onSurface,
        surfaceVariant = surfaceVariant,
        onSurfaceVariant = onSurfaceVariant,
        surfaceTint = surfaceTint,
        inverseSurface = inverseSurface,
        inverseOnSurface = inverseOnSurface,
        error = error,
        onError = onError,
        errorContainer = errorContainer,
        onErrorContainer = onErrorContainer,
        outline = outline,
        outlineVariant = outlineVariant,
        scrim = scrim,
        isLight = false
    )

val LocalColors = staticCompositionLocalOf { lightColorScheme() }
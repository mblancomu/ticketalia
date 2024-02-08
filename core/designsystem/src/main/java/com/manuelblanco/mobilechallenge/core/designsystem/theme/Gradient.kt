package com.manuelblanco.mobilechallenge.core.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

/**
 * Created by Manuel Blanco Murillo on 25/6/23.
 */

@Immutable
data class GradientColors(
    val top: Color = Color.Unspecified,
    val bottom: Color = Color.Unspecified,
    val container: Color = Color.Unspecified,
)

val LocalGradientColors = staticCompositionLocalOf { GradientColors() }
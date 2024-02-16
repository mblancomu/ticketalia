package com.manuelblanco.mobilechallenge.core.designsystem.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Created by Manuel Blanco Murillo on 9/2/24.
 */

data class TicketsDimensions(
    val paddingSmall: Dp = 4.dp,
    val paddingSmallMedium: Dp = 6.dp,
    val paddingSmallMediumDouble: Dp = 12.dp,
    val paddingMedium: Dp = 8.dp,
    val paddingMediumLarge: Dp = 10.dp,
    val paddingMediumDouble: Dp = 16.dp,
    val paddingLarge: Dp = 24.dp,
    val bottomBarHeight: Dp = 64.dp,
    val bottomBarItemHeight: Dp = 40.dp,
    val topAppBarHeight: Dp = 64.dp,
    val marginTopBar: Dp = 72.dp,
    val cardListHeight: Dp = 240.dp,
    val roundedCornerItemList: Dp = 12.dp,
    val roundedCornerDetail: Dp = 24.dp,
    val cardInfoHeight: Dp = 72.dp,
    val posterDetailHeight : Dp = 400.dp,
    val cardDetailPadding: Dp = 320.dp,
    val buttonDetailWidth: Dp = 130.dp,
    val progressBoxHeight: Dp = 68.dp,
    val progressBoxWidth: Dp = 200.dp,
    val progressBoxCorners: Dp = 40.dp
)

internal val LocalDimensions = staticCompositionLocalOf { TicketsDimensions() }
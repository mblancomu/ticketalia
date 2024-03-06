package com.manuelblanco.mobilechallenge.core.ui.components.bottombar

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.manuelblanco.mobilechallenge.core.common.utils.navigateSingleTopToGraph
import com.manuelblanco.mobilechallenge.core.designsystem.theme.TicketsTheme

/**
 * Created by Manuel Blanco Murillo on 5/2/24.
 */

@Composable
fun TicketsBottomBarItem(
    screen: TicketsBottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true

    val background =
        if (selected) TicketsTheme.colors.secondary else Color.Transparent

    val contentColor =
        if (selected) TicketsTheme.colors.background else TicketsTheme.colors.outline

    Box(
        modifier = Modifier
            .height(TicketsTheme.dimensions.bottomBarItemHeight)
            .clip(CircleShape)
            .background(background)
            .clickable(onClick = {
                navController.navigateSingleTopToGraph(screen.route)
            })
    ) {
        Row(
            modifier = Modifier
                .padding(
                    start = TicketsTheme.dimensions.paddingMediumLarge,
                    end = TicketsTheme.dimensions.paddingMediumLarge,
                    top = TicketsTheme.dimensions.paddingMedium,
                    bottom = TicketsTheme.dimensions.paddingMedium
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(TicketsTheme.dimensions.paddingSmallMedium)
        ) {
            Icon(
                painter = rememberVectorPainter(image = if (selected) screen.icon_focused else screen.icon),
                contentDescription = "icon",
                tint = contentColor
            )
            AnimatedVisibility(visible = selected) {
                Text(
                    text = stringResource(id = screen.title),
                    style = TicketsTheme.typography.bodyMedium,
                    color = contentColor
                )
            }
        }
    }
}

@SuppressLint("UnusedBoxWithConstraintsScope")
@Preview
@Composable
fun TicketsBottomBarItemComponentPreview(
) {
    BoxWithConstraints {
        TicketsTheme {
            val navController = rememberNavController()
            val navStackBackEntry by navController.currentBackStackEntryAsState()
            TicketsBottomBarItem(
                screen = TicketsBottomBarScreen.Events,
                currentDestination = navStackBackEntry?.destination,
                navController = navController
            )
        }
    }
}
package com.manuelblanco.mobilechallenge.core.ui.components.bottombar

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.manuelblanco.mobilechallenge.core.designsystem.theme.TicketsTheme

/**
 * Created by Manuel Blanco Murillo on 5/2/24.
 */
@Composable
fun TicketsBottomBar(modifier: Modifier, navController: NavHostController) {
    val screens = listOf(
        TicketsBottomBarScreen.Events,
        TicketsBottomBarScreen.Venues
    )

    val navStackBackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navStackBackEntry?.destination

    Row(
        modifier = modifier
            .height(TicketsTheme.dimensions.bottomBarHeight)
            .padding(0.dp)
            .background(TicketsTheme.colors.primary)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        screens.forEach { screen ->
            TicketsBottomBarItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@SuppressLint("UnusedBoxWithConstraintsScope")
@Preview
@Composable
fun TicketsBottomBarComponentPreview(
) {
    BoxWithConstraints {
        TicketsTheme {
            TicketsBottomBar(modifier = Modifier, navController = rememberNavController())
        }
    }
}
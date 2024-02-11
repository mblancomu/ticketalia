package com.manuelblanco.mobilechallenge.ui

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.manuelblanco.mobilechallenge.core.common.navigation.Navigation.Routes.EVENTS_START
import com.manuelblanco.mobilechallenge.core.common.navigation.Navigation.Routes.FAVORITES_START
import com.manuelblanco.mobilechallenge.core.common.navigation.Navigation.Routes.VENUES_START
import com.manuelblanco.mobilechallenge.core.ui.components.bottombar.TicketsBottomBar
import com.manuelblanco.mobilechallenge.navigation.TicketsNavigation

/**
 * Created by Manuel Blanco Murillo on 8/2/24.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicketsHomeScreen(
    windowSizeClass: WindowSizeClass,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    var bottomBarVisible by remember { mutableStateOf(false) }
    val bottomBarOffset by animateDpAsState(
        targetValue = if (bottomBarVisible) 0.dp else 64.dp,
        label = ""
    )
    val showBottomBar = windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (showBottomBar) {
                TicketsBottomBar(
                    modifier = if (bottomBarVisible)
                        modifier.navigationBarsPadding()
                    else
                        modifier.offset(y = bottomBarOffset), navController = navController
                )
            }
        }
    ) { innerPadding ->
        BoxWithConstraints(
            Modifier.padding(
                start = innerPadding.calculateStartPadding(LayoutDirection.Ltr),
                top = 0.dp,
                end = innerPadding.calculateEndPadding(LayoutDirection.Ltr),
                bottom = if (bottomBarVisible)
                    innerPadding.calculateBottomPadding()
                else
                    0.dp
            )
        ) {
            TicketsNavigation(navController = navController)
        }
    }

    navController.addOnDestinationChangedListener { _, destination, _ ->
        bottomBarVisible = when (destination.route) {
            EVENTS_START, VENUES_START, FAVORITES_START -> {
                true
            }

            else -> {
                false
            }
        }
    }
}
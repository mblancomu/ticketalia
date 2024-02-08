package com.manuelblanco.mobilechallenge.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController

/**
 * Created by Manuel Blanco Murillo on 30/6/23.
 */

@Composable
fun MainScreen(windowSizeClass: WindowSizeClass) {
    Surface(color = MaterialTheme.colorScheme.surface) {
        var showLandingScreen by remember { mutableStateOf(true) }

        if (showLandingScreen) {
            SplashScreen(onTimeout = { showLandingScreen = false })
        } else {
            val navController = rememberNavController()

            TicketsHomeScreen(
                windowSizeClass = windowSizeClass,
                modifier = Modifier,
                navController = navController
            )
        }
    }
}
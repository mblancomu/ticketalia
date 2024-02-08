package com.manuelblanco.mobilechallenge.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Stable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.manuelblanco.mobilechallenge.feature.events.navigation.EventDetailScreenDestination
import com.manuelblanco.mobilechallenge.feature.events.navigation.EventsScreenDestination

/**
 * Created by Manuel Blanco Murillo on 8/2/24.
 */

@Stable
fun NavGraphBuilder.favoritesGraph(
    navController: NavHostController,
    startDestination: String,
    route: String
) {
    navigation(startDestination = startDestination, route = route) {

    }
}
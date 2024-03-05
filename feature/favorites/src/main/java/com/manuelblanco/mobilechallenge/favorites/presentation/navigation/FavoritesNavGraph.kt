package com.manuelblanco.mobilechallenge.favorites.presentation.navigation

import androidx.compose.runtime.Stable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigation

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
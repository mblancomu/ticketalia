package com.manuelblanco.mobilechallenge.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.manuelblanco.mobilechallenge.core.common.navigation.Navigation
import com.manuelblanco.mobilechallenge.core.common.navigation.Navigation.Routes.EVENTS_START
import com.manuelblanco.mobilechallenge.core.common.navigation.Navigation.Routes.FAVORITES_HOME
import com.manuelblanco.mobilechallenge.core.common.navigation.Navigation.Routes.FAVORITES_START
import com.manuelblanco.mobilechallenge.core.common.navigation.Navigation.Routes.VENUES_HOME
import com.manuelblanco.mobilechallenge.core.common.navigation.Navigation.Routes.VENUES_START
import com.manuelblanco.mobilechallenge.favorites.navigation.favoritesGraph
import com.manuelblanco.mobilechallenge.feature.events.navigation.eventsGraph
import com.manuelblanco.mobilechallenge.feature.venues.navigation.venuesGraph

/**
 * Created by Manuel Blanco Murillo on 31/1/24.
 */

@Composable
fun TicketsNavigation(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Navigation.Routes.EVENTS_HOME
    ) {

        eventsGraph(
            navController = navController,
            startDestination = EVENTS_START,
            route = Navigation.Routes.EVENTS_HOME
        )

        venuesGraph(
            navController = navController,
            startDestination = VENUES_START,
            route = VENUES_HOME
        )

        favoritesGraph(
            navController = navController,
            startDestination = FAVORITES_START,
            route = FAVORITES_HOME
        )
    }
}
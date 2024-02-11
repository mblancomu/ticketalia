package com.manuelblanco.mobilechallenge.feature.events.navigation

import androidx.compose.runtime.Stable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.manuelblanco.mobilechallenge.core.common.navigation.Navigation
import com.manuelblanco.mobilechallenge.core.common.navigation.navigateToEventDetail

/**
 * Created by Manuel Blanco Murillo on 8/2/24.
 */

@Stable
fun NavGraphBuilder.eventsGraph(
    navController: NavHostController,
    startDestination: String,
    route: String
) {
    navigation(startDestination = startDestination, route = route) {
        composable(
            route = Navigation.Routes.EVENTS_START
        ) {
            EventsScreenDestination { id, title ->
                navController.navigateToEventDetail(id, title)
            }
        }

        composable(
            route = Navigation.Routes.EVENT_DETAIL,
            arguments = listOf(
                navArgument(name = Navigation.Args.EVENT_ID) {
                    type = NavType.StringType
                },
                navArgument(name = Navigation.Args.EVENT_TITLE) {
                    type = NavType.StringType
                })
        ) { backStackEntry ->
            val eventId =
                requireNotNull(backStackEntry.arguments?.getString(Navigation.Args.EVENT_ID)) { "Event id is required as an argument" }
            val eventTitle =
                requireNotNull(backStackEntry.arguments?.getString(Navigation.Args.EVENT_TITLE)) { "Event title is required as an argument" }
            EventDetailScreenDestination(
                eventId = eventId,
                eventTitle = eventTitle,
                navigateTo = {
                    navController.popBackStack()
                },
            )
        }
    }
}
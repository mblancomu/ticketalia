package com.manuelblanco.mobilechallenge.feature.venues.navigation

import androidx.compose.runtime.Stable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.manuelblanco.mobilechallenge.core.common.navigation.Navigation
import com.manuelblanco.mobilechallenge.core.common.navigation.navigateToVenueDetail

/**
 * Created by Manuel Blanco Murillo on 8/2/24.
 */

@Stable
fun NavGraphBuilder.venuesGraph(
    navController: NavHostController,
    startDestination: String,
    route: String
) {
    navigation(startDestination = startDestination, route = route) {
        composable(
            route = Navigation.Routes.VENUES_START
        ) {
            VenuesScreenDestination { id, title ->
                navController.navigateToVenueDetail(id, title)
            }
        }

        composable(
            route = Navigation.Routes.VENUE_DETAIL,
            arguments = listOf(
                navArgument(name = Navigation.Args.VENUE_ID) {
                    type = NavType.StringType
                },
                navArgument(name = Navigation.Args.VENUE_TITLE) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val venueId =
                requireNotNull(backStackEntry.arguments?.getString(Navigation.Args.VENUE_ID)) { "Venue id is required as an argument" }
            val venueTitle =
                requireNotNull(backStackEntry.arguments?.getString(Navigation.Args.VENUE_TITLE)) { "Venue title is required as an argument" }
            VenueDetailScreenDestination(
                venueId = venueId,
                venueTitle = venueTitle,
                navigateTo = {
                    navController.popBackStack()
                },
            )
        }
    }
}
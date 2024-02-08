package com.manuelblanco.mobilechallenge.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.manuelblanco.mobilechallenge.ui.navigation.Navigation.Args.EVENT_ID
import com.manuelblanco.mobilechallenge.ui.navigation.Navigation.Args.EVENT_TITLE
import com.manuelblanco.mobilechallenge.ui.navigation.Navigation.Args.VENUE_ID
import com.manuelblanco.mobilechallenge.ui.navigation.Navigation.Args.VENUE_TITLE
import com.manuelblanco.mobilechallenge.ui.navigation.Navigation.Routes.EVENTS_START
import com.manuelblanco.mobilechallenge.ui.navigation.Navigation.Routes.FAVORITES_HOME
import com.manuelblanco.mobilechallenge.ui.navigation.Navigation.Routes.FAVORITES_START
import com.manuelblanco.mobilechallenge.ui.navigation.Navigation.Routes.VENUES_HOME
import com.manuelblanco.mobilechallenge.ui.navigation.Navigation.Routes.VENUES_START

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

object Navigation {

    object Args {
        const val EVENT_ID = "event_id"
        const val VENUE_ID = "venue_id"
        const val FAVORITE_ID = "favorite_id"
        const val VENUE_TITLE = "venueTitle"
        const val EVENT_TITLE = "eventTitle"
    }

    object Routes {
        const val EVENTS_HOME = "home/eventsHome"
        const val EVENTS_START = "home/eventsHome/events"
        const val VENUES_HOME = "home/venuesHome"
        const val VENUES_START = "home/venuesHome/venues"
        const val FAVORITES_HOME = "home/favoritesHome"
        const val FAVORITES_START = "home/favoritesHome/favorites"
        const val EVENT_DETAIL = "$EVENTS_HOME/{$EVENT_ID}/{$EVENT_TITLE}"
        const val VENUE_DETAIL = "$VENUES_HOME/{$VENUE_ID}/{$VENUE_TITLE}"

    }

}

fun NavController.navigateToEventDetail(eventId: String, eventTitle: String) {
    navigate(route = "${Navigation.Routes.EVENTS_HOME}/$eventId/$eventTitle")
}

fun NavController.navigateToVenueDetail(venueId: String, venueTitle: String) {
    navigate(route = "${Navigation.Routes.VENUES_HOME}/$venueId/$venueTitle")
}
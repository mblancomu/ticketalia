package com.manuelblanco.mobilechallenge.core.common.navigation

import androidx.navigation.NavController
import com.manuelblanco.mobilechallenge.core.common.navigation.Navigation.Args.EVENT_ID
import com.manuelblanco.mobilechallenge.core.common.navigation.Navigation.Args.EVENT_TITLE
import com.manuelblanco.mobilechallenge.core.common.navigation.Navigation.Args.VENUE_ID
import com.manuelblanco.mobilechallenge.core.common.navigation.Navigation.Args.VENUE_TITLE

/**
 * Created by Manuel Blanco Murillo on 9/2/24.
 */

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

fun NavController.navigateToFavoriteDetail(favoriteId: String, favoriteTitle: String) {
    navigate(route = "${Navigation.Routes.FAVORITES_HOME}/$favoriteId/$favoriteTitle")
}
package com.manuelblanco.mobilechallenge.core.ui.components.bottombar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.outlined.Event
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Place
import androidx.compose.ui.graphics.vector.ImageVector
import com.manuelblanco.mobilechallenge.core.common.navigation.Navigation
import com.manuelblanco.mobilechallenge.core.ui.R

sealed class TicketsBottomBarScreen(
    val route: String,
    val title: Int,
    val icon: ImageVector,
    val icon_focused: ImageVector
) {

    object Events : TicketsBottomBarScreen(
        route = Navigation.Routes.EVENTS_HOME,
        title = R.string.events,
        icon = Icons.Outlined.Event,
        icon_focused = Icons.Filled.Event
    )

    object Venues : TicketsBottomBarScreen(
        route = Navigation.Routes.VENUES_HOME,
        title = R.string.venues,
        icon = Icons.Outlined.Place,
        icon_focused = Icons.Filled.Place
    )

    object Favorites : TicketsBottomBarScreen(
        route = Navigation.Routes.FAVORITES_HOME,
        title = R.string.favorites,
        icon = Icons.Outlined.Favorite,
        icon_focused = Icons.Filled.Favorite
    )

}

package com.manuelblanco.mobilechallenge.feature.venues.navigation

import androidx.compose.runtime.Composable
import com.manuelblanco.mobilechallenge.feature.venues.composables.VenueDetailScreen
import com.manuelblanco.mobilechallenge.feature.venues.composables.VenuesScreen
import com.manuelblanco.mobilechallenge.feature.venues.presentation.VenueDetailContract
import com.manuelblanco.mobilechallenge.feature.venues.presentation.VenuesContract

/**
 * Created by Manuel Blanco Murillo on 6/7/23.
 */

@Composable
fun VenueDetailScreenDestination(venueId: String, venueTitle: String,  navigateTo: () -> Unit) {
    VenueDetailScreen(
        venueId = venueId,
        venueTitle = venueTitle,
        onNavigationRequested = { navigationEffect ->
            if (navigationEffect is VenueDetailContract.Effect.Navigation.Back) {
                navigateTo()
            }
        }
    )
}
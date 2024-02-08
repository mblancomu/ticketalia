package com.manuelblanco.mobilechallenge.feature.venues.navigation

import androidx.compose.runtime.Composable
import com.manuelblanco.mobilechallenge.feature.venues.composables.VenuesScreen
import com.manuelblanco.mobilechallenge.feature.venues.presentation.VenuesContract

/**
 * Created by Manuel Blanco Murillo on 6/7/23.
 */

@Composable
fun VenuesScreenDestination(navigateTo: (id: String, title: String) -> Unit) {
    VenuesScreen(
        onNavigationRequested = { navigationEffect ->
            if (navigationEffect is VenuesContract.Effect.Navigation.ToVenue) {
                navigateTo(navigationEffect.venueId, navigationEffect.venueTitle)
            }
        }
    )
}
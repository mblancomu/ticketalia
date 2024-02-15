package com.manuelblanco.mobilechallenge.feature.venues.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.manuelblanco.mobilechallenge.feature.venues.composables.VenueDetailScreen
import com.manuelblanco.mobilechallenge.feature.venues.presentation.VenueDetailContract
import com.manuelblanco.mobilechallenge.feature.venues.presentation.VenueDetailViewModel

/**
 * Created by Manuel Blanco Murillo on 6/7/23.
 */

@Composable
fun VenueDetailScreenDestination(
    venueDetailViewModel: VenueDetailViewModel = hiltViewModel(),
    venueId: String,
    venueTitle: String,
    navigateTo: () -> Unit
) {
    val state by venueDetailViewModel.viewState.collectAsStateWithLifecycle()
    val venue = state.venue
    val effect = venueDetailViewModel.effect

    VenueDetailScreen(
        venueId = venueId,
        venueTitle = venueTitle,
        stateUi = state,
        venue = venue,
        effect = effect,
        onNavigationRequested = { navigationEffect ->
            if (navigationEffect is VenueDetailContract.Effect.Navigation.Back) {
                navigateTo()
            }
        },
        onSendEvent = { event ->  venueDetailViewModel.setEvent(event)},
        onGetVenue = { id -> venueDetailViewModel.getVenue(id)}
    )
}
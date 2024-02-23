package com.manuelblanco.mobilechallenge.feature.venues.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import com.manuelblanco.mobilechallenge.feature.venues.composables.VenuesScreen
import com.manuelblanco.mobilechallenge.feature.venues.presentation.VenuesContract
import com.manuelblanco.mobilechallenge.feature.venues.presentation.VenuesViewModel

/**
 * Created by Manuel Blanco Murillo on 6/7/23.
 */

@Composable
fun VenuesScreenDestination(
    venuesViewModel: VenuesViewModel = hiltViewModel(),
    navigateTo: (id: String, title: String) -> Unit
) {
    val venues = venuesViewModel.venues.collectAsLazyPagingItems()
    val state = venuesViewModel.viewState.collectAsStateWithLifecycle()
    val effect = venuesViewModel.effect

    VenuesScreen(
        venues = venues,
        effect = effect,
        stateUi = state.value,
        onSendEvent = { event -> venuesViewModel.setEvent(event) },
    ) { navigationEffect ->
        if (navigationEffect is VenuesContract.Effect.Navigation.ToVenue) {
            navigateTo(navigationEffect.venueId, navigationEffect.venueTitle)
        }
    }
}
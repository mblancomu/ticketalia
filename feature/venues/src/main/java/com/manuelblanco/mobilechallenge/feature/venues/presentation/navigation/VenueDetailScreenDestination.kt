package com.manuelblanco.mobilechallenge.feature.venues.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.manuelblanco.mobilechallenge.core.common.utils.launchGoogleMaps
import com.manuelblanco.mobilechallenge.core.domain.model.toGoogleUri
import com.manuelblanco.mobilechallenge.feature.venues.presentation.composables.VenueDetailScreen
import com.manuelblanco.mobilechallenge.feature.venues.presentation.contracts.VenueDetailContract
import com.manuelblanco.mobilechallenge.feature.venues.presentation.viewmodels.VenueDetailViewModel

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

    val uriHandler = LocalUriHandler.current
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        venueDetailViewModel.getVenue(venueId)
    }

    VenueDetailScreen(
        venueTitle = venueTitle,
        stateUi = state,
        effect = venueDetailViewModel.effect,
        onNavigationRequested = { navigationEffect ->
            when (navigationEffect) {
                is VenueDetailContract.Effect.Navigation.Back -> {
                    navigateTo()
                }

                is VenueDetailContract.Effect.Navigation.Localization -> {
                    launchGoogleMaps(
                        state.venue?.location?.toGoogleUri().toString(),
                        context
                    )
                }

                is VenueDetailContract.Effect.Navigation.Info -> {
                    uriHandler.openUri(state.venue?.url.toString())
                }
            }
        },
        onSendEvent = { event -> venueDetailViewModel.setEvent(event) }
    )
}
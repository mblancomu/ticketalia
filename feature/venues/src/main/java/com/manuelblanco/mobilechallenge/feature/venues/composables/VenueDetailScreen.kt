package com.manuelblanco.mobilechallenge.feature.venues.composables

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.manuelblanco.mobilechallenge.core.common.utils.launchGoogleMaps
import com.manuelblanco.mobilechallenge.core.model.data.toGoogleUri
import com.manuelblanco.mobilechallenge.core.ui.components.Progress
import com.manuelblanco.mobilechallenge.core.ui.components.TicketsTopBar
import com.manuelblanco.mobilechallenge.core.ui.mvi.SIDE_EFFECTS_KEY
import com.manuelblanco.mobilechallenge.feature.venues.presentation.VenueDetailContract
import com.manuelblanco.mobilechallenge.feature.venues.presentation.VenueDetailViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

/**
 * Created by Manuel Blanco Murillo on 7/2/24.
 */

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VenueDetailScreen(
    venueId: String,
    venueTitle: String,
    onNavigationRequested: (navigationEffect: VenueDetailContract.Effect.Navigation) -> Unit,
    venueDetailViewModel: VenueDetailViewModel = hiltViewModel()
) {
    val state by venueDetailViewModel.viewState.collectAsStateWithLifecycle()
    val venue = state.venue
    val effect = venueDetailViewModel.effect

    val uriHandler = LocalUriHandler.current
    val context = LocalContext.current

    LaunchedEffect(SIDE_EFFECTS_KEY) {
        effect.onEach { effect ->
            when (effect) {
                is VenueDetailContract.Effect.Navigation.Back -> {
                    onNavigationRequested(effect)
                }

                VenueDetailContract.Effect.Navigation.Info -> {
                    uriHandler.openUri(venue?.url.toString())
                }

                VenueDetailContract.Effect.Navigation.Localization -> {
                    launchGoogleMaps(
                        venue?.location?.toGoogleUri().toString(),
                        context
                    )
                }
            }
        }.collect()
    }

    Scaffold(
        topBar = {
            TicketsTopBar(
                isCentered = false,
                isNavigable = true,
                containerColor = Color.Transparent,
                onBack = { venueDetailViewModel.setEvent(VenueDetailContract.Event.BackButtonClicked) })
        }
    ) {
        when {
            state.isError -> {

            }

            state.isLoading -> {
                Progress()
            }

            state.isInit -> {
                venueDetailViewModel.getVenue(venueId)
            }

            else -> {
                VenueDetailContent(venue, venueTitle, venueDetailViewModel)
            }
        }
    }
}
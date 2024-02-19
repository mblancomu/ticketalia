package com.manuelblanco.mobilechallenge.feature.events.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.manuelblanco.mobilechallenge.core.common.utils.launchGoogleMaps
import com.manuelblanco.mobilechallenge.core.model.data.toGoogleUri
import com.manuelblanco.mobilechallenge.feature.events.composables.EventDetailScreen
import com.manuelblanco.mobilechallenge.feature.events.presentation.EventDetailContract
import com.manuelblanco.mobilechallenge.feature.events.presentation.EventDetailViewModel

/**
 * Created by Manuel Blanco Murillo on 6/7/23.
 */

@Composable
fun EventDetailScreenDestination(
    eventDetailViewModel: EventDetailViewModel = hiltViewModel(),
    eventId: String,
    eventTitle: String,
    navigateTo: () -> Unit
) {

    val state by eventDetailViewModel.viewState.collectAsStateWithLifecycle()

    val uriHandler = LocalUriHandler.current
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        eventDetailViewModel.getEvent(eventId)
    }

    EventDetailScreen(
        eventTitle = eventTitle,
        stateUi = state,
        effect = eventDetailViewModel.effect,
        onNavigationRequested = { navigationEffect ->
            when(navigationEffect){
                is EventDetailContract.Effect.Navigation.Back -> { navigateTo() }
                is EventDetailContract.Effect.Navigation.Localization -> {
                    launchGoogleMaps(
                        state.event?.location?.toGoogleUri().toString(),
                        context
                    )
                }
                is EventDetailContract.Effect.Navigation.Tickets -> {
                    uriHandler.openUri(state.event?.url.toString())
                }
            }
        },
        onSendEvent = { launch -> eventDetailViewModel.setEvent(launch) }
    )
}
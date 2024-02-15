package com.manuelblanco.mobilechallenge.feature.events.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
    val event = state.event
    val effect = eventDetailViewModel.effect

    EventDetailScreen(
        eventId = eventId,
        eventTitle = eventTitle,
        event = event,
        stateUi = state,
        effect = effect,
        onNavigationRequested = { navigationEffect ->
            if (navigationEffect is EventDetailContract.Effect.Navigation.Back) {
                navigateTo()
            }
        },
        onSendEvent = { launch -> eventDetailViewModel.setEvent(launch) },
        onGetEvent = { id -> eventDetailViewModel.getEvent(id) }
    )
}
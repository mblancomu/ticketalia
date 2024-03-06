package com.manuelblanco.mobilechallenge.feature.events.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.manuelblanco.mobilechallenge.feature.events.presentation.composables.EventsScreen
import com.manuelblanco.mobilechallenge.feature.events.presentation.contracts.EventsContract
import com.manuelblanco.mobilechallenge.feature.events.presentation.viewmodels.EventsViewModel

/**
 * Created by Manuel Blanco Murillo on 6/7/23.
 */

@Composable
fun EventsScreenDestination(
    viewModel: EventsViewModel = hiltViewModel(),
    navigateTo: (id: String, title: String) -> Unit
) {
    val state by viewModel.viewState.collectAsStateWithLifecycle()
    val effect = viewModel.effect

    EventsScreen(
        stateUi = state,
        effect = effect,
        onNavigationRequested = { navigationEffect ->
            if (navigationEffect is EventsContract.Effect.Navigation.ToEvent) {
                navigateTo(navigationEffect.eventId, navigationEffect.eventTitle)
            }
        },
        onSendEvent = { event -> viewModel.setEvent(event) },
    )
}
package com.manuelblanco.mobilechallenge.feature.events.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.manuelblanco.mobilechallenge.feature.events.composables.EventsScreen
import com.manuelblanco.mobilechallenge.feature.events.presentation.EventsContract
import com.manuelblanco.mobilechallenge.feature.events.presentation.EventsViewModel

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
    val isRefreshing by viewModel.isRefreshing.collectAsStateWithLifecycle()

    EventsScreen(
        stateUi = state,
        effect = effect,
        isRefreshing = isRefreshing,
        onNavigationRequested = { navigationEffect ->
            if (navigationEffect is EventsContract.Effect.Navigation.ToEvent) {
                navigateTo(navigationEffect.eventId, navigationEffect.eventTitle)
            }
        },
        onRefresh = { viewModel.refresh() },
        onSendEvent = { event -> viewModel.setEvent(event) },
        onPaginate = { viewModel.getMoreEvents() }
    )
}
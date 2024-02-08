package com.manuelblanco.mobilechallenge.feature.events.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.manuelblanco.mobilechallenge.feature.events.composables.EventsScreen
import com.manuelblanco.mobilechallenge.feature.events.presentation.EventsContract
import com.manuelblanco.mobilechallenge.feature.events.presentation.EventsViewModel

/**
 * Created by Manuel Blanco Murillo on 6/7/23.
 */

@Composable
fun EventsScreenDestination(navigateTo: (id: String, title: String) -> Unit) {
    EventsScreen(
        onNavigationRequested = { navigationEffect ->
            if (navigationEffect is EventsContract.Effect.Navigation.ToEvent) {
                navigateTo(navigationEffect.eventId, navigationEffect.eventTitle)
            }
        }
    )
}
package com.manuelblanco.mobilechallenge.feature.events.navigation

import androidx.compose.runtime.Composable
import com.manuelblanco.mobilechallenge.feature.events.composables.EventDetailScreen
import com.manuelblanco.mobilechallenge.feature.events.presentation.EventDetailContract

/**
 * Created by Manuel Blanco Murillo on 6/7/23.
 */

@Composable
fun EventDetailScreenDestination(eventId: String, eventTitle: String, navigateTo: () -> Unit) {
    EventDetailScreen(
        eventId = eventId,
        eventTitle = eventTitle,
        onNavigationRequested = { navigationEffect ->
            if (navigationEffect is EventDetailContract.Effect.Navigation.Back) {
                navigateTo()
            }
        }
    )
}
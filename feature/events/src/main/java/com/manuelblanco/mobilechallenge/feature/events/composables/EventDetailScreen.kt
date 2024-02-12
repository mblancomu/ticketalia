package com.manuelblanco.mobilechallenge.feature.events.composables

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
import com.manuelblanco.mobilechallenge.feature.events.presentation.EventDetailContract
import com.manuelblanco.mobilechallenge.feature.events.presentation.EventDetailViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

/**
 * Created by Manuel Blanco Murillo on 7/2/24.
 */

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EventDetailScreen(
    eventId: String,
    eventTitle: String,
    onNavigationRequested: (navigationEffect: EventDetailContract.Effect.Navigation) -> Unit,
    eventDetailViewModel: EventDetailViewModel = hiltViewModel()
) {
    val state by eventDetailViewModel.viewState.collectAsStateWithLifecycle()
    val event = state.event
    val effect = eventDetailViewModel.effect

    val uriHandler = LocalUriHandler.current
    val context = LocalContext.current

    LaunchedEffect(SIDE_EFFECTS_KEY) {
        effect.onEach { effect ->
            when (effect) {
                is EventDetailContract.Effect.Navigation.Back -> {
                    onNavigationRequested(effect)
                }

                EventDetailContract.Effect.Navigation.Localization -> {
                    launchGoogleMaps(
                        event?.location?.toGoogleUri().toString(),
                        context
                    )
                }

                EventDetailContract.Effect.Navigation.Tickets -> {
                    uriHandler.openUri(event?.url.toString())
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
                onBack = { eventDetailViewModel.setEvent(EventDetailContract.Event.GoBack) })
        }
    ) {
        when {
            state.isError -> {}
            state.isLoading -> {
                Progress()
            }

            state.isInit -> {
                eventDetailViewModel.getEvent(eventId)
            }

            else -> {
                EventDetailContent(event, eventTitle, eventDetailViewModel)
            }
        }
    }
}
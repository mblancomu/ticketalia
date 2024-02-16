package com.manuelblanco.mobilechallenge.feature.events.composables

import android.annotation.SuppressLint
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import com.manuelblanco.mobilechallenge.core.common.utils.launchGoogleMaps
import com.manuelblanco.mobilechallenge.core.model.data.Event
import com.manuelblanco.mobilechallenge.core.model.data.toGoogleUri
import com.manuelblanco.mobilechallenge.core.ui.components.Progress
import com.manuelblanco.mobilechallenge.core.ui.components.TicketsTopBar
import com.manuelblanco.mobilechallenge.core.ui.mvi.SIDE_EFFECTS_KEY
import com.manuelblanco.mobilechallenge.core.ui.mvi.SIDE_STATES_KEY
import com.manuelblanco.mobilechallenge.feature.events.R
import com.manuelblanco.mobilechallenge.feature.events.presentation.EventDetailContract
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

/**
 * Created by Manuel Blanco Murillo on 7/2/24.
 */

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EventDetailScreen(
    eventId: String,
    eventTitle: String,
    event: Event?,
    stateUi: EventDetailContract.State,
    effect: Flow<EventDetailContract.Effect>?,
    onSendEvent: (EventDetailContract.Event) -> Unit,
    onGetEvent: (id: String) -> Unit,
    onNavigationRequested: (navigationEffect: EventDetailContract.Effect.Navigation) -> Unit
) {

    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val snackBarMessage = stringResource(R.string.global_error)

    val uriHandler = LocalUriHandler.current
    val context = LocalContext.current

    LaunchedEffect(SIDE_EFFECTS_KEY) {
        effect?.onEach { effect ->
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
        }?.collect()
    }

    LaunchedEffect(SIDE_STATES_KEY) {
        when {
            stateUi.isError -> {
                scaffoldState.snackbarHostState.showSnackbar(
                    message = snackBarMessage,
                    duration = SnackbarDuration.Short
                )
            }
        }
    }

    Scaffold(
        topBar = {
            TicketsTopBar(
                isCentered = false,
                isNavigable = true,
                containerColor = Color.Transparent,
                onBack = { onSendEvent(EventDetailContract.Event.GoBack) })
        }
    ) {
        when {
            stateUi.isLoading -> {
                Progress()
            }

            stateUi.isInit -> {
                onGetEvent(eventId)
            }

            else -> {
                EventDetailContent(event, eventTitle, onSendEvent)
            }
        }
    }
}
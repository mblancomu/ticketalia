package com.manuelblanco.mobilechallenge.feature.events.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.manuelblanco.mobilechallenge.core.designsystem.theme.TicketsTheme
import com.manuelblanco.mobilechallenge.core.model.data.Event
import com.manuelblanco.mobilechallenge.core.testing.data.eventDetail
import com.manuelblanco.mobilechallenge.core.ui.components.Progress
import com.manuelblanco.mobilechallenge.core.ui.components.TicketsTopBar
import com.manuelblanco.mobilechallenge.core.ui.mvi.SIDE_EFFECTS_KEY
import com.manuelblanco.mobilechallenge.core.ui.mvi.SIDE_STATES_KEY
import com.manuelblanco.mobilechallenge.feature.events.R
import com.manuelblanco.mobilechallenge.feature.events.presentation.EventDetailContract
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach

/**
 * Created by Manuel Blanco Murillo on 7/2/24.
 */

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState")
@Composable
fun EventDetailScreen(
    eventTitle: String,
    stateUi: EventDetailContract.State,
    effect: Flow<EventDetailContract.Effect>?,
    onSendEvent: (EventDetailContract.Event) -> Unit,
    onNavigationRequested: (navigationEffect: EventDetailContract.Effect.Navigation) -> Unit
) {

    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val snackBarMessage = stringResource(R.string.global_error)

    LaunchedEffect(SIDE_EFFECTS_KEY) {
        effect?.onEach { effect ->
            when (effect) {
                is EventDetailContract.Effect.Navigation -> {
                    onNavigationRequested(effect)
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

            else -> {
                EventDetailContent(stateUi.event, eventTitle, onSendEvent)
            }
        }
    }
}

@SuppressLint("UnusedBoxWithConstraintsScope")
@Preview
@Composable
fun EventsDetailScreenPreviewIdle(
    event: Event = eventDetail.data
) {
    BoxWithConstraints {
        TicketsTheme {
            EventDetailScreen(
                eventTitle = event.name,
                stateUi = EventDetailContract.State(
                    event = event,
                    isLoading = false,
                    isError = false
                ),
                effect = flow { },
                onSendEvent = {},
                onNavigationRequested = {}
            )
        }
    }
}
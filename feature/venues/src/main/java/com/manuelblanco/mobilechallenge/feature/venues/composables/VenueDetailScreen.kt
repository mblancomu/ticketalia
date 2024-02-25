package com.manuelblanco.mobilechallenge.feature.venues.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import com.manuelblanco.mobilechallenge.core.designsystem.theme.TicketsTheme
import com.manuelblanco.mobilechallenge.core.model.data.Venue
import com.manuelblanco.mobilechallenge.core.testing.data.venueDetail
import com.manuelblanco.mobilechallenge.core.ui.components.Progress
import com.manuelblanco.mobilechallenge.core.ui.components.TicketsTopBar
import com.manuelblanco.mobilechallenge.core.ui.mvi.SIDE_EFFECTS_KEY
import com.manuelblanco.mobilechallenge.core.ui.mvi.SIDE_STATES_KEY
import com.manuelblanco.mobilechallenge.feature.venues.R
import com.manuelblanco.mobilechallenge.feature.venues.presentation.VenueDetailContract
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach

/**
 * Created by Manuel Blanco Murillo on 7/2/24.
 */

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun VenueDetailScreen(
    venueTitle: String,
    stateUi: VenueDetailContract.State,
    effect: Flow<VenueDetailContract.Effect>?,
    onSendEvent: (VenueDetailContract.Event) -> Unit,
    onNavigationRequested: (navigationEffect: VenueDetailContract.Effect.Navigation) -> Unit
) {

    val snackBarHostState = remember { SnackbarHostState() }
    val snackBarMessage = stringResource(R.string.global_error)

    LaunchedEffect(SIDE_EFFECTS_KEY) {
        effect?.onEach { effect ->
            when (effect) {
                is VenueDetailContract.Effect.Navigation -> {
                    onNavigationRequested(effect)
                }
            }
        }?.collect()
    }

    LaunchedEffect(SIDE_STATES_KEY) {
        when {
            stateUi.isError -> {
                snackBarHostState.showSnackbar(
                    message = snackBarMessage,
                    duration = androidx.compose.material3.SnackbarDuration.Short
                )
            }
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                modifier = Modifier.semantics { contentDescription = "SnackBar" },
                hostState = snackBarHostState
            )
        },
        topBar = {
            TicketsTopBar(
                isCentered = false,
                isNavigable = true,
                containerColor = Color.Transparent,
                onBack = { onSendEvent(VenueDetailContract.Event.BackButtonClicked) })
        }
    ) {
        when {
            stateUi.isLoading -> {
                Progress()
            }

            else -> {
                VenueDetailContent(stateUi.venue, venueTitle, onSendEvent)
            }
        }
    }
}

@SuppressLint("UnusedBoxWithConstraintsScope")
@Preview
@Composable
fun VenuesDetailScreenPreviewIdle(
    venue: Venue = venueDetail.data
) {
    BoxWithConstraints {
        TicketsTheme {
            VenueDetailScreen(
                venueTitle = venue.name,
                stateUi = VenueDetailContract.State(
                    venue = venue,
                    isLoading = false,
                    isError = false,
                ),
                effect = flow {},
                onSendEvent = {},
                onNavigationRequested = {}
            )
        }
    }
}
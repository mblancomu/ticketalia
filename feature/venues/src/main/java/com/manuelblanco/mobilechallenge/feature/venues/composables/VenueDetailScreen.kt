package com.manuelblanco.mobilechallenge.feature.venues.composables

import android.annotation.SuppressLint
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.manuelblanco.mobilechallenge.core.ui.components.Progress
import com.manuelblanco.mobilechallenge.core.ui.components.TicketsTopBar
import com.manuelblanco.mobilechallenge.core.ui.mvi.SIDE_EFFECTS_KEY
import com.manuelblanco.mobilechallenge.core.ui.mvi.SIDE_STATES_KEY
import com.manuelblanco.mobilechallenge.feature.venues.R
import com.manuelblanco.mobilechallenge.feature.venues.presentation.VenueDetailContract
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
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

    val scaffoldState: ScaffoldState = rememberScaffoldState()
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
package com.manuelblanco.mobilechallenge.feature.venues.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.manuelblanco.mobilechallenge.core.ui.components.TicketsTopBar
import com.manuelblanco.mobilechallenge.core.ui.mvi.SIDE_EFFECTS_KEY
import com.manuelblanco.mobilechallenge.feature.venues.presentation.VenueDetailContract
import com.manuelblanco.mobilechallenge.feature.venues.presentation.VenueDetailViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

/**
 * Created by Manuel Blanco Murillo on 7/2/24.
 */

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VenueDetailScreen(
    venueId: String,
    venueTitle: String,
    onNavigationRequested: (navigationEffect: VenueDetailContract.Effect.Navigation) -> Unit,
    venueDetailViewModel: VenueDetailViewModel = hiltViewModel()
) {

    val state by venueDetailViewModel.viewState.collectAsStateWithLifecycle()
    val venue = state.venue
    val effect = venueDetailViewModel.effect

    LaunchedEffect(SIDE_EFFECTS_KEY) {
        effect.onEach { effect ->
            when (effect) {
                is VenueDetailContract.Effect.Navigation.Back -> {
                    onNavigationRequested(effect)
                }
            }
        }.collect()
    }

    Scaffold(
        topBar = {
            TicketsTopBar(
                isCentered = false,
                title = venueTitle,
                isNavigable = true,
                onBack = { venueDetailViewModel.setEvent(VenueDetailContract.Event.BackButtonClicked) },
            )
        }
    ) {
        when {
            state.isError -> {}
            state.isLoading -> {
                Progress()
            }

            state.isInit -> {
                venueDetailViewModel.getVenue(venueId)
            }

            else -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "${venue?.name} ${venue?.city} ${venue?.country}")
                }
            }
        }
    }
}

@Composable
fun Progress() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}
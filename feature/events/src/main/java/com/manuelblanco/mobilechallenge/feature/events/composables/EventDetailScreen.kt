package com.manuelblanco.mobilechallenge.feature.events.composables

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

    LaunchedEffect(SIDE_EFFECTS_KEY) {
        effect.onEach { effect ->
            when (effect) {
                is EventDetailContract.Effect.Navigation.Back -> {
                    onNavigationRequested(effect)
                }
            }
        }.collect()
    }

    Scaffold(
        topBar = {
            TicketsTopBar(
                isCentered = false,
                title = eventTitle,
                isNavigable = true,
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
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "${event?.name} ${event?.city} ${event?.country}")
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
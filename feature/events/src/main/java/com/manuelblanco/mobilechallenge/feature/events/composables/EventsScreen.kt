package com.manuelblanco.mobilechallenge.feature.events.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.manuelblanco.mobilechallenge.core.designsystem.theme.TicketsTheme
import com.manuelblanco.mobilechallenge.core.ui.components.Progress
import com.manuelblanco.mobilechallenge.core.ui.components.TicketsTopBar
import com.manuelblanco.mobilechallenge.core.ui.mvi.SIDE_EFFECTS_KEY
import com.manuelblanco.mobilechallenge.feature.events.presentation.EventsContract
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

/**
 * Created by Manuel Blanco Murillo on 27/6/23.
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun EventsScreen(
    stateUi: EventsContract.State,
    effect: Flow<EventsContract.Effect>?,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    onPaginate: () -> Unit,
    onSendEvent: (EventsContract.Event) -> Unit,
    onNavigationRequested: (navigationEffect: EventsContract.Effect.Navigation) -> Unit
) {

    val gridState = rememberLazyGridState()
    val pullRefreshState = rememberPullRefreshState(isRefreshing, { onRefresh() })

    var showProgress by remember { mutableStateOf(false) }

    LaunchedEffect(SIDE_EFFECTS_KEY) {
        effect?.onEach { effect ->
            when (effect) {
                is EventsContract.Effect.DataWasLoaded -> {
                }

                is EventsContract.Effect.Navigation.ToEvent -> onNavigationRequested(effect)
            }
        }?.collect()
    }

    Scaffold(
        topBar = {
            TicketsTopBar(isCentered = true)
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = TicketsTheme.dimensions.topAppBarHeight)
                    .background(TicketsTheme.colors.surface),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .pullRefresh(pullRefreshState),
                    contentAlignment = Alignment.Center
                ) {
                    LazyEventsGrid(
                        state = gridState,
                        events = stateUi.events,
                        isLoading = stateUi.isLoading,
                        page = stateUi.page,
                        getEvents = { onPaginate() },
                        onItemClick = { id, title ->
                            onSendEvent(EventsContract.Event.EventSelection(id, title))
                        })
                    if (showProgress) {
                        Progress()
                        showProgress = false
                    }

                    PullRefreshIndicator(
                        isRefreshing,
                        pullRefreshState,
                        Modifier.align(Alignment.TopCenter)
                    )
                }

                showProgress = when {
                    stateUi.isLoading -> true
                    stateUi.isError -> {
                        false
                    }

                    else -> {
                        false
                    }
                }
            }

        }
    )
}
package com.manuelblanco.mobilechallenge.feature.events.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarDuration.*
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.manuelblanco.mobilechallenge.core.designsystem.theme.TicketsTheme
import com.manuelblanco.mobilechallenge.core.ui.components.EndlessLazyColumn
import com.manuelblanco.mobilechallenge.core.ui.components.ErrorRow
import com.manuelblanco.mobilechallenge.core.ui.components.ItemType
import com.manuelblanco.mobilechallenge.core.ui.components.Progress
import com.manuelblanco.mobilechallenge.core.ui.components.ShimmerEffectList
import com.manuelblanco.mobilechallenge.core.ui.components.TicketsTopBar
import com.manuelblanco.mobilechallenge.core.ui.mvi.SIDE_EFFECTS_KEY
import com.manuelblanco.mobilechallenge.feature.events.R
import com.manuelblanco.mobilechallenge.feature.events.presentation.EventsContract
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

/**
 * Created by Manuel Blanco Murillo on 27/6/23.
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EventsScreen(
    stateUi: EventsContract.State,
    effect: Flow<EventsContract.Effect>?,
    onRefresh: () -> Unit,
    onPaginate: () -> Unit,
    onSendEvent: (EventsContract.Event) -> Unit,
    onNavigationRequested: (navigationEffect: EventsContract.Effect.Navigation) -> Unit
) {

    val snackbarHostState = remember { SnackbarHostState() }
    val snackBarMessage = stringResource(R.string.global_error)

    val gridState = rememberLazyGridState()
    val listState = rememberLazyListState()
    val pullRefreshState =
        rememberPullRefreshState(stateUi.isRefreshing, { onSendEvent(EventsContract.Event.Refresh) })
    var isDataLoaded by remember { mutableStateOf(false) }

    LaunchedEffect(SIDE_EFFECTS_KEY) {
        effect?.onEach { effect ->
            when (effect) {
                is EventsContract.Effect.DataWasLoaded -> {
                    isDataLoaded = true
                }

                is EventsContract.Effect.Navigation.ToEvent -> onNavigationRequested(effect)
                is EventsContract.Effect.RefreshingData -> onRefresh()
            }
        }?.collect()
    }

    if (stateUi.isError) {
        LaunchedEffect(snackbarHostState) {
            snackbarHostState.showSnackbar(
                message = snackBarMessage,
                duration = SnackbarDuration.Short
            )
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
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

                    if (isShimmerVisible(stateUi.events.isEmpty(), isDataLoaded, stateUi.isRefreshing)) {
                        ShimmerEffectList(type = ItemType.EVENT)
                    }

                    EndlessLazyColumn(
                        loading = stateUi.isLoading,
                        listState = listState,
                        items = stateUi.events,
                        itemContent = { event ->
                            EventContent(
                                Modifier.height(TicketsTheme.dimensions.cardListHeight),
                                event,
                            ) { id, title ->
                                onSendEvent(EventsContract.Event.EventSelection(id, title))
                            }
                        },
                        emptyList = { ErrorRow(stringResource(R.string.no_events_found)) },
                        loadMore = { onPaginate() },
                        loadingItem = {
                            Box(modifier = Modifier.height(160.dp)) {
                                Progress()
                            }
                        }
                    )

                    PullRefreshIndicator(
                        stateUi.isRefreshing,
                        pullRefreshState,
                        Modifier.align(Alignment.TopCenter)
                    )
                }

            }

        }
    )
}

private fun isShimmerVisible(isEmpty: Boolean, isLoaded: Boolean, isRefreshing: Boolean): Boolean =
    isEmpty && !isLoaded || isRefreshing
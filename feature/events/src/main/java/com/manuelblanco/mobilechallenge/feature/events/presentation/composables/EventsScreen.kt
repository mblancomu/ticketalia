package com.manuelblanco.mobilechallenge.feature.events.presentation.composables

import android.annotation.SuppressLint
import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.manuelblanco.mobilechallenge.core.designsystem.theme.TicketsTheme
import com.manuelblanco.mobilechallenge.core.domain.model.Cities
import com.manuelblanco.mobilechallenge.core.domain.model.Event
import com.manuelblanco.mobilechallenge.core.domain.model.EventsFilter
import com.manuelblanco.mobilechallenge.core.domain.model.SortType
import com.manuelblanco.mobilechallenge.core.testing.data.eventsFromCacheList
import com.manuelblanco.mobilechallenge.core.ui.components.EmptyListScreen
import com.manuelblanco.mobilechallenge.core.ui.components.EndlessLazyColumn
import com.manuelblanco.mobilechallenge.core.ui.components.ItemType
import com.manuelblanco.mobilechallenge.core.ui.components.Progress
import com.manuelblanco.mobilechallenge.core.ui.components.ShimmerEffectList
import com.manuelblanco.mobilechallenge.core.ui.components.TicketsPullRefresh
import com.manuelblanco.mobilechallenge.core.ui.components.TicketsSearchBar
import com.manuelblanco.mobilechallenge.core.ui.components.TicketsTopBar
import com.manuelblanco.mobilechallenge.core.ui.mvi.SIDE_EFFECTS_KEY
import com.manuelblanco.mobilechallenge.feature.events.R
import com.manuelblanco.mobilechallenge.feature.events.presentation.contracts.EventsContract
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

/**
 * Created by Manuel Blanco Murillo on 27/6/23.
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EventsScreen(
    stateUi: EventsContract.State,
    effect: Flow<EventsContract.Effect>?,
    onSendEvent: (EventsContract.Event) -> Unit,
    onNavigationRequested: (navigationEffect: EventsContract.Effect.Navigation) -> Unit
) {

    val snackBarHostState = remember { SnackbarHostState() }
    val snackBarMessage = stringResource(R.string.global_error)
    val keyboardController = LocalSoftwareKeyboardController.current

    var showFilters by remember { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()

    var searchJob: Job? = null

    val listState = rememberLazyListState()
    val pullRefreshState =
        rememberPullRefreshState(stateUi.isRefreshing,
            { onSendEvent(EventsContract.Event.Refresh) })
    var isDataLoaded by remember { mutableStateOf(false) }
    var searchQuery by rememberSaveable { mutableStateOf("") }

    LaunchedEffect(SIDE_EFFECTS_KEY) {
        effect?.onEach { effect ->
            when (effect) {
                is EventsContract.Effect.DataWasLoaded -> {
                    isDataLoaded = true
                }

                is EventsContract.Effect.Navigation.ToEvent -> onNavigationRequested(effect)
            }
        }?.collect()
    }

    if (showFilters) {
        EventsFilterModal(
            onApply = {
                onSendEvent(EventsContract.Event.Filter(it))
                showFilters = false
            },
            onDismiss = { showFilters = false }
        )
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                modifier = Modifier.semantics { contentDescription = "SnackBar" },
                hostState = snackBarHostState
            )
        },
        topBar = {
            TicketsTopBar(isCentered = true, searchBar = {
                TicketsSearchBar(
                    text = searchQuery,
                    placeHolder = stringResource(id = R.string.search_events),
                    onTextChange = {
                        searchQuery = it
                        searchJob?.cancel()
                        searchJob = coroutineScope.launch {
                            searchQuery.let { query ->
                                kotlinx.coroutines.delay(500L)
                                onSendEvent(EventsContract.Event.Search(query = query))
                            }
                        }
                    },
                    onCloseClicked = {
                        if (searchQuery.isNotEmpty()) {
                            onSendEvent(EventsContract.Event.Search(query = ""))
                        }
                        searchQuery = ""
                        keyboardController?.hide()
                    },
                    onSearchClicked = {
                        onSendEvent(EventsContract.Event.Search(query = it))
                        keyboardController?.hide()
                    },
                    onFilterClicked = { showFilters = true }
                )
            })
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

                    when (stateUi.screenState) {

                        is EventsContract.State.ScreenState.Error -> {
                            LaunchedEffect(snackBarHostState) {
                                snackBarHostState.showSnackbar(
                                    message = snackBarMessage,
                                    duration = SnackbarDuration.Short
                                )
                            }
                        }

                        EventsContract.State.ScreenState.Idle,
                        is EventsContract.State.ScreenState.Loading -> {
                            if (stateUi.isRefreshing) searchQuery = ""
                            ShimmerEffectList(
                                type = ItemType.EVENT,
                                isVisible = stateUi.events.isEmpty()
                            )
                        }

                        is EventsContract.State.ScreenState.Empty -> {
                            if (stateUi.isRefreshing) searchQuery = ""
                            EmptyListScreen(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(TicketsTheme.colors.background),
                                title = stringResource(id = R.string.no_events_found)
                            )
                        }
                    }

                    EndlessLazyColumn(
                        loading = stateUi.isPaginating,
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
                        loadMore = { onSendEvent(EventsContract.Event.Paginate) },
                        loadingItem = {
                            Box(modifier = Modifier.height(160.dp)) {
                                Progress()
                            }
                        }
                    )

                    TicketsPullRefresh(
                        modifier = Modifier.align(Alignment.TopCenter),
                        isRefreshing = stateUi.isRefreshing,
                        state = pullRefreshState
                    )
                }

            }

        }
    )
}

@SuppressLint("UnusedBoxWithConstraintsScope")
@Preview
@Composable
fun EventsScreenPreviewPopulated(
    events: List<Event> = eventsFromCacheList
) {
    BoxWithConstraints {
        TicketsTheme {
            EventsScreen(
                stateUi = EventsContract.State(
                    events = eventsFromCacheList,
                    screenState = EventsContract.State.ScreenState.Loading,
                    filter = EventsFilter(sortType = SortType.NONE, city = Cities.ALL.city),
                    keyword = "",
                    isRefreshing = false,
                    isPaginating = false
                ),
                effect = flow { },
                onSendEvent = {},
                onNavigationRequested = {}
            )
        }
    }
}
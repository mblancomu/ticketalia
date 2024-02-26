package com.manuelblanco.mobilechallenge.feature.venues.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.manuelblanco.mobilechallenge.core.designsystem.theme.TicketsTheme
import com.manuelblanco.mobilechallenge.core.model.data.Venue
import com.manuelblanco.mobilechallenge.core.testing.data.pagingVenues
import com.manuelblanco.mobilechallenge.core.ui.components.ItemType
import com.manuelblanco.mobilechallenge.core.ui.components.ShimmerEffectList
import com.manuelblanco.mobilechallenge.core.ui.components.TicketsPullRefresh
import com.manuelblanco.mobilechallenge.core.ui.components.TicketsSearchBar
import com.manuelblanco.mobilechallenge.core.ui.components.TicketsTopBar
import com.manuelblanco.mobilechallenge.core.ui.mvi.SIDE_EFFECTS_KEY
import com.manuelblanco.mobilechallenge.feature.venues.R
import com.manuelblanco.mobilechallenge.feature.venues.presentation.VenuesContract
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach

/**
 * Created by Manuel Blanco Murillo on 27/6/23.
 */
@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun VenuesScreen(
    venues: LazyPagingItems<Venue>,
    stateUi: VenuesContract.State,
    effect: Flow<VenuesContract.Effect>?,
    onSendEvent: (VenuesContract.Event) -> Unit,
    onNavigationRequested: (navigationEffect: VenuesContract.Effect.Navigation) -> Unit
) {

    val snackbarHostState = remember { SnackbarHostState() }
    val snackBarMessage = stringResource(R.string.global_error)
    val keyboardController = LocalSoftwareKeyboardController.current

    val pullRefreshState = rememberPullRefreshState(
        stateUi.isRefreshing,
        {
            venues.refresh()
            onSendEvent(VenuesContract.Event.Refresh)
        })

    var isDataLoaded by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }

    LaunchedEffect(SIDE_EFFECTS_KEY) {
        effect?.onEach { effect ->
            when (effect) {
                is VenuesContract.Effect.DataWasLoaded -> {
                    isDataLoaded = true
                }

                is VenuesContract.Effect.Navigation.ToVenue -> onNavigationRequested(effect)
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
        snackbarHost = {
            SnackbarHost(
                modifier = Modifier.semantics { contentDescription = "SnackBar" },
                hostState = snackbarHostState
            )
        },
        topBar = {
            TicketsTopBar(isCentered = true, searchBar = {
                TicketsSearchBar(
                    text = searchQuery,
                    placeHolder = stringResource(id = R.string.search_venues),
                    onTextChange = {
                        searchQuery = it
                        if (searchQuery.length > 3) {
                            onSendEvent(VenuesContract.Event.Search(query = it))
                        }
                    },
                    onCloseClicked = {
                        searchQuery = ""
                        keyboardController?.hide()
                    },
                    onSearchClicked = {
                        onSendEvent(VenuesContract.Event.Search(query = it))
                        keyboardController?.hide()
                    }
                ) {

                }
            })
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = TicketsTheme.dimensions.topAppBarHeight)
                .background(TicketsTheme.colors.surface),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                Modifier
                    .pullRefresh(pullRefreshState)
            ) {

                if (isShimmerVisible(
                        isEmpty = venues.itemCount == 0,
                        isRefreshing = venues.loadState.refresh is LoadState.Loading
                    )
                ) {
                    ShimmerEffectList(type = ItemType.VENUE)
                } else {
                    VenuesLazyList(
                        venues = venues,
                        onSendVenue = { onSendEvent(it) })
                }

                TicketsPullRefresh(
                    modifier = Modifier.align(Alignment.TopCenter),
                    isRefreshing = stateUi.isRefreshing,
                    state = pullRefreshState
                )
            }
        }
    }
}

private fun isShimmerVisible(isEmpty: Boolean, isRefreshing: Boolean): Boolean =
    isEmpty || isRefreshing

@SuppressLint("UnusedBoxWithConstraintsScope")
@Preview
@Composable
fun VenuesScreenPreviewPopulated(
    venues: LazyPagingItems<Venue> = flow { emit(pagingVenues) }.collectAsLazyPagingItems()
) {
    BoxWithConstraints {
        TicketsTheme {
            VenuesScreen(
                venues = venues,
                stateUi = VenuesContract.State(
                    isLoading = false,
                    isRefreshing = false,
                    isError = false
                ),
                effect = flow { },
                onSendEvent = {},
                onNavigationRequested = {}
            )
        }
    }
}
package com.manuelblanco.mobilechallenge.feature.venues.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.manuelblanco.mobilechallenge.core.designsystem.theme.TicketsTheme
import com.manuelblanco.mobilechallenge.core.model.data.Venue
import com.manuelblanco.mobilechallenge.core.ui.components.EmptyListScreen
import com.manuelblanco.mobilechallenge.core.ui.components.ErrorScreen
import com.manuelblanco.mobilechallenge.core.ui.components.LoadingScreen
import com.manuelblanco.mobilechallenge.core.ui.components.Progress
import com.manuelblanco.mobilechallenge.core.ui.components.TicketsTopBar
import com.manuelblanco.mobilechallenge.feature.venues.R
import com.manuelblanco.mobilechallenge.feature.venues.presentation.VenuesContract

/**
 * Created by Manuel Blanco Murillo on 27/6/23.
 */
@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun VenuesScreen(
    venues: LazyPagingItems<Venue>,
    stateUi: VenuesContract.State,
    onNavigationRequested: (navigationEffect: VenuesContract.Effect.Navigation) -> Unit
) {

    val snackbarHostState = remember { SnackbarHostState() }
    val snackBarMessage = stringResource(R.string.global_error)

    val pullRefreshState = rememberPullRefreshState(
        venues.loadState.refresh is LoadState.Loading,
        { venues.refresh() })

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
    ) {
        Box(
            Modifier
                .pullRefresh(pullRefreshState)
        ) {
            if (stateUi.isLoading) {
                LoadingScreen(
                    title = stringResource(id = R.string.initial_loading),
                    modifier = Modifier.fillMaxHeight()
                )
            } else {
                if (venues.itemCount == 0) {
                    EmptyListScreen(
                        title = stringResource(id = R.string.empty_list_venues),
                        modifier = Modifier.fillMaxSize()
                    )
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .background(TicketsTheme.colors.surface)
                            .padding(
                                top = TicketsTheme.dimensions.marginTopBar,
                                bottom = TicketsTheme.dimensions.paddingMedium
                            )
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(
                            TicketsTheme.dimensions.paddingMedium,
                            Alignment.CenterVertically
                        ),
                    ) {
                        items(venues.itemCount) { index ->
                            val venue = venues[index]
                            venue?.let {
                                VenueContent(venue = venue, onVenueClicked = {
                                    onNavigationRequested(
                                        VenuesContract.Effect.Navigation.ToVenue(
                                            venue.id,
                                            venue.name
                                        )
                                    )
                                })
                            }
                        }

                        val loadState = venues.loadState.mediator
                        item {
                            if (loadState?.refresh == LoadState.Loading) {
                                LoadingScreen(
                                    title = stringResource(id = R.string.refresh_loading),
                                    modifier = Modifier.fillParentMaxSize()
                                )
                            }

                            if (loadState?.append == LoadState.Loading) {
                                Progress()
                            }

                            if (loadState?.refresh is LoadState.Error || loadState?.append is LoadState.Error) {
                                val isPaginatingError =
                                    (loadState.append is LoadState.Error) || venues.itemCount > 1
                                val error = if (loadState.append is LoadState.Error)
                                    (loadState.append as LoadState.Error).error
                                else
                                    (loadState.refresh as LoadState.Error).error

                                val modifier = if (isPaginatingError) {
                                    Modifier.padding(TicketsTheme.dimensions.paddingMedium)
                                } else {
                                    Modifier.fillParentMaxSize()
                                }

                                ErrorScreen(
                                    modifier = modifier,
                                    isPaginatingError = isPaginatingError,
                                    message = error.message ?: error.toString()
                                )
                            }
                        }
                    }
                }
            }

            PullRefreshIndicator(
                modifier = Modifier.align(Alignment.TopCenter),
                refreshing = venues.loadState.refresh is LoadState.Loading,
                state = pullRefreshState,
                contentColor = TicketsTheme.colors.primary
            )
        }
    }
}
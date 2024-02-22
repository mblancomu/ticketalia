package com.manuelblanco.mobilechallenge.feature.venues.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.manuelblanco.mobilechallenge.core.designsystem.theme.TicketsTheme
import com.manuelblanco.mobilechallenge.core.model.data.Venue
import com.manuelblanco.mobilechallenge.core.ui.components.ItemType
import com.manuelblanco.mobilechallenge.core.ui.components.ShimmerEffectList
import com.manuelblanco.mobilechallenge.core.ui.components.TicketsTopBar
import com.manuelblanco.mobilechallenge.core.ui.mvi.SIDE_EFFECTS_KEY
import com.manuelblanco.mobilechallenge.feature.venues.R
import com.manuelblanco.mobilechallenge.feature.venues.presentation.VenuesContract
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
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
    onRefresh: () -> Unit,
    onSendEvent: (VenuesContract.Event) -> Unit,
    onNavigationRequested: (navigationEffect: VenuesContract.Effect.Navigation) -> Unit
) {

    val snackbarHostState = remember { SnackbarHostState() }
    val snackBarMessage = stringResource(R.string.global_error)

    val pullRefreshState = rememberPullRefreshState(
        stateUi.isRefreshing,
        { onSendEvent(VenuesContract.Event.Refresh) })

    var isDataLoaded by remember { mutableStateOf(false) }

    LaunchedEffect(SIDE_EFFECTS_KEY) {
        effect?.onEach { effect ->
            when (effect) {
                is VenuesContract.Effect.DataWasLoaded -> {
                    isDataLoaded = true
                }

                is VenuesContract.Effect.Navigation.ToVenue -> onNavigationRequested(effect)
                is VenuesContract.Effect.RefreshingData -> onRefresh()
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

                if (isShimmerVisible(isDataLoaded, stateUi.isRefreshing)) {
                    ShimmerEffectList(type = ItemType.VENUE)
                }

                VenuesLazyList(
                    venues = venues,
                    onSendVenue = { onSendEvent(it) })

                PullRefreshIndicator(
                    modifier = Modifier.align(Alignment.TopCenter),
                    refreshing = venues.loadState.refresh is LoadState.Loading,
                    state = pullRefreshState,
                    contentColor = TicketsTheme.colors.primary
                )
            }
        }
    }
}

private fun isShimmerVisible(isLoaded: Boolean, isRefreshing: Boolean): Boolean =
    !isLoaded || isRefreshing
package com.manuelblanco.mobilechallenge.feature.venues.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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
import com.manuelblanco.mobilechallenge.core.ui.components.ItemType
import com.manuelblanco.mobilechallenge.core.ui.components.Progress
import com.manuelblanco.mobilechallenge.core.ui.components.ShimmerItemList
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
            if (venues.itemCount == 0 && stateUi.isLoading) {
                ShimmerItemList(type = ItemType.VENUE)
            }

            if (venues.itemCount == 0 && !stateUi.isLoading) {
                EmptyListScreen(
                    title = stringResource(id = R.string.empty_list_venues),
                    modifier = Modifier.fillMaxSize()
                )
            }

            VenuesLazyList(
                venues = venues,
                onNavigationRequested = { onNavigationRequested(it) })

            if (venues.loadState.refresh is LoadState.Loading){
                Progress()
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
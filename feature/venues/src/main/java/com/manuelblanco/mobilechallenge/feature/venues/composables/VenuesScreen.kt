package com.manuelblanco.mobilechallenge.feature.venues.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.manuelblanco.mobilechallenge.core.designsystem.theme.TicketsTheme
import com.manuelblanco.mobilechallenge.core.ui.components.Progress
import com.manuelblanco.mobilechallenge.core.ui.components.TicketsTopBar
import com.manuelblanco.mobilechallenge.feature.venues.presentation.VenuesContract
import com.manuelblanco.mobilechallenge.feature.venues.presentation.VenuesViewModel

/**
 * Created by Manuel Blanco Murillo on 27/6/23.
 */
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun VenuesScreen(
    onNavigationRequested: (navigationEffect: VenuesContract.Effect.Navigation) -> Unit,
    venuesViewModel: VenuesViewModel = hiltViewModel()
) {

    val venues = venuesViewModel.venues.collectAsLazyPagingItems()
    val pullRefreshState = rememberPullRefreshState(
        venues.loadState.refresh is LoadState.Loading,
        { venues.refresh() })

    Scaffold(
        topBar = {
            TicketsTopBar(isCentered = true)
        },
    ) {
        Box(
            Modifier
                .pullRefresh(pullRefreshState)
        ) {
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
                items(
                    items = venues
                ) { venue ->
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
                        Column(
                            modifier = Modifier
                                .fillParentMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            Text(
                                modifier = Modifier
                                    .padding(TicketsTheme.dimensions.paddingMedium),
                                text = "Refresh Loading"
                            )

                            Progress()
                        }
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
                        Column(
                            modifier = modifier,
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            if (!isPaginatingError) {
                                Icon(
                                    modifier = Modifier
                                        .size(TicketsTheme.dimensions.bottomBarHeight),
                                    imageVector = Icons.Rounded.Warning, contentDescription = null
                                )
                            }

                            Text(
                                modifier = Modifier
                                    .padding(TicketsTheme.dimensions.paddingMedium),
                                text = error.message ?: error.toString(),
                                textAlign = TextAlign.Center,
                            )
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
package com.manuelblanco.mobilechallenge.feature.venues.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.manuelblanco.mobilechallenge.core.designsystem.theme.TicketsTheme
import com.manuelblanco.mobilechallenge.core.model.data.Venue
import com.manuelblanco.mobilechallenge.core.ui.components.ErrorScreen
import com.manuelblanco.mobilechallenge.core.ui.components.Progress
import com.manuelblanco.mobilechallenge.feature.venues.presentation.VenuesContract

/**
 * Created by Manuel Blanco Murillo on 19/2/24.
 */

@Composable
fun VenuesLazyList(
    venues: LazyPagingItems<Venue>,
    onNavigationRequested: (navigationEffect: VenuesContract.Effect.Navigation) -> Unit
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
            if (loadState?.refresh is LoadState.Loading || loadState?.append is LoadState.Loading) {
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
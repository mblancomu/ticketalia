package com.manuelblanco.mobilechallenge.feature.events.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.manuelblanco.mobilechallenge.core.data.mediator.PAGE_SIZE
import com.manuelblanco.mobilechallenge.core.model.data.Event
import com.manuelblanco.mobilechallenge.core.ui.components.ErrorRow
import com.manuelblanco.mobilechallenge.feature.events.R

/**
 * Created by Manuel Blanco Murillo on 31/1/24.
 */

@Composable
fun LazyEventsGrid(
    state: LazyGridState,
    events: List<Event>,
    isLoading: Boolean = false,
    page: Int,
    getEvents: () -> Unit,
    onItemClick: (id: String, title: String) -> Unit
) {
    val COLUMN_COUNT = 1
    val GRID_SPACING = 8.dp

    LazyVerticalGrid(
        columns = GridCells.Fixed(COLUMN_COUNT),
        contentPadding = PaddingValues(
            start = GRID_SPACING,
            end = GRID_SPACING,
            top = GRID_SPACING,
            bottom = 8.dp,
        ),
        horizontalArrangement = Arrangement.spacedBy(GRID_SPACING, Alignment.CenterHorizontally),
        verticalArrangement = Arrangement.spacedBy(GRID_SPACING, Alignment.CenterVertically),
        state = state,
    ) {
        itemsIndexed(items = events) { index, event ->
            if (!isLoading && ((index + 1) >= (page * PAGE_SIZE))) {
                getEvents()
            }

            if (events.isEmpty()) {
                ErrorRow(stringResource(R.string.no_events_found))
            } else {
                EventContent(event, Modifier.height(240.dp)
                ) { id, title ->
                    onItemClick(id, title)
                }
            }
        }

    }
}
package com.manuelblanco.mobilechallenge.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import com.manuelblanco.mobilechallenge.core.designsystem.theme.TicketsTheme

/**
 * Created by Manuel Blanco Murillo on 21/2/24.
 */


@Composable
fun <T> EndlessLazyColumn(
    modifier: Modifier = Modifier,
    loading: Boolean = false,
    listState: LazyListState = rememberLazyListState(),
    items: List<T>,
    itemContent: @Composable (T) -> Unit,
    loadingItem: @Composable () -> Unit,
    emptyList: @Composable () -> Unit,
    loadMore: () -> Unit
) {

    val reachedBottom: Boolean by remember { derivedStateOf { listState.reachedBottom() } }

    // load more if scrolled to bottom
    LaunchedEffect(reachedBottom) {
        if (reachedBottom && !loading) loadMore()
    }

    LazyColumn(
        modifier = modifier.semantics { contentDescription = "Lazy List" },
        state = listState,
        contentPadding = PaddingValues(
            start = TicketsTheme.dimensions.paddingMedium,
            end = TicketsTheme.dimensions.paddingMedium,
            top = TicketsTheme.dimensions.paddingMedium,
            bottom = TicketsTheme.dimensions.paddingMedium,
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            TicketsTheme.dimensions.paddingMedium,
            Alignment.CenterVertically
        )
    ) {
        itemsIndexed(
            items = items,
        ) { index, item ->
            if (items.isEmpty() && !loading) {
                emptyList()
            } else {
                Column {
                    itemContent(item)
                    if (loading && index == items.size - 1) {
                        loadingItem()
                    }
                }
            }
        }
    }
}

private fun LazyListState.reachedBottom(): Boolean {
    val lastVisibleItem = this.layoutInfo.visibleItemsInfo.lastOrNull()
    return lastVisibleItem?.index != 0 && lastVisibleItem?.index == this.layoutInfo.totalItemsCount - 1
}
package com.manuelblanco.mobilechallenge.core.ui.components

import androidx.compose.foundation.layout.Box

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.LineHeightStyle
import com.manuelblanco.mobilechallenge.core.designsystem.theme.TicketsTheme
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.ui.Alignment

/**
 * Created by Manuel Blanco Murillo on 25/2/24.
 */

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TicketsPullRefresh(modifier: Modifier = Modifier, isRefreshing: Boolean, state: PullRefreshState){
    Box {
        PullRefreshIndicator(
            modifier = modifier
                .semantics { contentDescription = "Pull Refresh" },
            refreshing = isRefreshing,
            state = state,
            contentColor = TicketsTheme.colors.primary
        )
    }

}



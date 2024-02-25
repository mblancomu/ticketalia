package com.manuelblanco.venues.screenshot

import androidx.paging.compose.collectAsLazyPagingItems
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.android.ide.common.rendering.api.SessionParams
import com.manuelblanco.mobilechallenge.core.designsystem.theme.TicketsTheme
import com.manuelblanco.mobilechallenge.core.testing.data.pagingVenues
import com.manuelblanco.mobilechallenge.core.testing.data.pagingVenuesEmpty
import com.manuelblanco.mobilechallenge.feature.venues.composables.VenuesScreen
import com.manuelblanco.mobilechallenge.feature.venues.presentation.VenuesContract
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test

/**
 * Created by Manuel Blanco Murillo on 15/2/24.
 */

class VenuesScreenPaparazziTests {

    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = DeviceConfig.NEXUS_5,
        renderingMode = SessionParams.RenderingMode.NORMAL,
        showSystemUi = false,
        maxPercentDifference = 1.0,
    )

    @Test
    fun venuesScreen_ListPopulated() {
        paparazzi.snapshot {
            TicketsTheme {
                VenuesScreen(
                    venues = flowOf(pagingVenues).collectAsLazyPagingItems(),
                    stateUi = VenuesContract.State(
                        isLoading = false,
                        isError = false,
                        isRefreshing = false
                    ),
                    effect = flow {},
                    onSendEvent = {}
                ) {}
            }
        }
    }

    @Test
    fun venuesScreen_EmptyList() {
        paparazzi.snapshot {
            TicketsTheme {
                VenuesScreen(
                    venues = flowOf(pagingVenuesEmpty).collectAsLazyPagingItems(),
                    stateUi = VenuesContract.State(
                        isLoading = false,
                        isError = false,
                        isRefreshing = false
                    ),
                    effect = flow {},
                    onSendEvent = {}
                ) {}
            }
        }
    }

    @Test
    fun venuesScreen_ShowError() {
        paparazzi.snapshot {
            TicketsTheme {
                VenuesScreen(
                    venues = flowOf(pagingVenuesEmpty).collectAsLazyPagingItems(),
                    stateUi = VenuesContract.State(
                        isLoading = false,
                        isError = true,
                        isRefreshing = false
                    ),
                    effect = flow {},
                    onSendEvent = {}
                ) {}
            }
        }
    }

    @Test
    fun venuesScreen_isLoading() {
        paparazzi.snapshot {
            TicketsTheme {
                VenuesScreen(
                    venues = flowOf(pagingVenuesEmpty).collectAsLazyPagingItems(),
                    stateUi = VenuesContract.State(
                        isLoading = true,
                        isError = false,
                        isRefreshing = false
                    ),
                    effect = flow {},
                    onSendEvent = {}
                ) {}
            }
        }
    }

}
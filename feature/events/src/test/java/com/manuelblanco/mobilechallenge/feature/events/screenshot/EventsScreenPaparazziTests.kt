package com.manuelblanco.mobilechallenge.feature.events.screenshot

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.android.ide.common.rendering.api.SessionParams
import com.manuelblanco.mobilechallenge.core.designsystem.theme.TicketsTheme
import com.manuelblanco.mobilechallenge.core.model.data.Cities
import com.manuelblanco.mobilechallenge.core.model.data.EventsFilter
import com.manuelblanco.mobilechallenge.core.model.data.SortType
import com.manuelblanco.mobilechallenge.core.testing.data.eventsFromCacheList
import com.manuelblanco.mobilechallenge.feature.events.composables.EventsScreen
import com.manuelblanco.mobilechallenge.feature.events.presentation.EventsContract
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test

/**
 * Created by Manuel Blanco Murillo on 15/2/24.
 */

class EventsScreenPaparazziTests {

    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = DeviceConfig.NEXUS_5,
        renderingMode = SessionParams.RenderingMode.NORMAL,
        showSystemUi = false,
        maxPercentDifference = 1.0,
    )

    @Test
    fun eventsScreen_ListPopulated() {
        paparazzi.snapshot {
            TicketsTheme {
                EventsScreen(
                    stateUi = EventsContract.State(
                        events = eventsFromCacheList,
                        keyword = "",
                        filters = EventsFilter(
                            sortType = SortType.NAME,
                            city = Cities.ALL.city
                        ),
                        isLoading = false,
                        isRefreshing = false,
                        isSearching = false,
                        isError = false,
                        page = 1
                    ),
                    effect = flowOf(EventsContract.Effect.Navigation.ToEvent("1", "Hi")),
                    onSendEvent = {},
                    onNavigationRequested = {}
                )
            }
        }
    }

    @Test
    fun eventsScreen_EmptyList() {
        paparazzi.snapshot {
            TicketsTheme {
                EventsScreen(
                    stateUi = EventsContract.State(
                        events = emptyList(),
                        keyword = "",
                        filters = EventsFilter(
                            sortType = SortType.NAME,
                            city = Cities.ALL.city
                        ),
                        isLoading = false,
                        isRefreshing = false,
                        isSearching = false,
                        isError = false
                    ),
                    effect = flowOf(EventsContract.Effect.DataWasLoaded),
                    onSendEvent = {},
                    onNavigationRequested = {}
                )
            }
        }
    }

    @Test
    fun eventsScreen_LoadingItems() {
        paparazzi.snapshot {
            TicketsTheme {
                EventsScreen(
                    stateUi = EventsContract.State(
                        events = emptyList(),
                        keyword = "",
                        filters = EventsFilter(
                            sortType = SortType.NAME,
                            city = Cities.ALL.city
                        ),
                        isLoading = true,
                        isRefreshing = false,
                        isSearching = false,
                        isError = false
                    ),
                    effect = flowOf(EventsContract.Effect.Navigation.ToEvent("1", "Hi")),
                    onSendEvent = {},
                    onNavigationRequested = {}
                )
            }
        }
    }

    @Test
    fun eventsScreen_Error() {
        paparazzi.snapshot {
            TicketsTheme {
                EventsScreen(
                    stateUi = EventsContract.State(
                        events = emptyList(),
                        keyword = "",
                        filters = EventsFilter(
                            sortType = SortType.NAME,
                            city = Cities.ALL.city
                        ),
                        isLoading = false,
                        isRefreshing = false,
                        isSearching = false,
                        isError = true
                    ),
                    effect = flowOf(EventsContract.Effect.Navigation.ToEvent("1", "Hi")),
                    onSendEvent = {},
                    onNavigationRequested = {}
                )
            }
        }
    }

    @Test
    fun eventsScreen_SwipeRefreshing() {
        paparazzi.snapshot {
            TicketsTheme {
                EventsScreen(
                    stateUi = EventsContract.State(
                        events = eventsFromCacheList,
                        keyword = "",
                        filters = EventsFilter(
                            sortType = SortType.NAME,
                            city = Cities.ALL.city
                        ),
                        isLoading = false,
                        isRefreshing = true,
                        isSearching = false,
                        isError = false
                    ),
                    effect = flowOf(EventsContract.Effect.Navigation.ToEvent("1", "Hi")),
                    onSendEvent = {},
                    onNavigationRequested = {}
                )
            }
        }
    }

}
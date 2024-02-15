package com.manuelblanco.mobilechallenge.feature.events.screenshot

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.android.ide.common.rendering.api.SessionParams
import com.manuelblanco.mobilechallenge.core.designsystem.theme.TicketsTheme
import com.manuelblanco.mobilechallenge.core.testing.data.eventsFromCacheList
import com.manuelblanco.mobilechallenge.feature.events.composables.EventDetailScreen
import com.manuelblanco.mobilechallenge.feature.events.presentation.EventDetailContract
import org.junit.Rule
import org.junit.Test

/**
 * Created by Manuel Blanco Murillo on 15/2/24.
 */

class EventDetailScreenPaparazziTests {

    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = DeviceConfig.NEXUS_5,
        renderingMode = SessionParams.RenderingMode.NORMAL,
        showSystemUi = false,
        maxPercentDifference = 1.0,
    )

    @Test
    fun venueDetailScreen_ShowingDetail() {
        paparazzi.snapshot {
            TicketsTheme {
                EventDetailScreen(
                    eventId = "1",
                    eventTitle = "Camela",
                    event = eventsFromCacheList[0],
                    stateUi = EventDetailContract.State(
                        event = eventsFromCacheList[0],
                        isInit = false,
                        isLoading = false,
                        isError = false
                    ),
                    effect = null,
                    onSendEvent = {},
                    onGetEvent = {},
                    onNavigationRequested = {}
                )
            }
        }
    }

    @Test
    fun venueDetailScreen_LoadingDetail() {
        paparazzi.snapshot {
            TicketsTheme {
                EventDetailScreen(
                    eventId = "1",
                    eventTitle = "Camela",
                    event = eventsFromCacheList[0],
                    stateUi = EventDetailContract.State(
                        event = eventsFromCacheList[0],
                        isInit = false,
                        isLoading = true,
                        isError = false
                    ),
                    effect = null,
                    onSendEvent = {},
                    onGetEvent = {},
                    onNavigationRequested = {}
                )
            }
        }
    }

}
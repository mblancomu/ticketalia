package com.manuelblanco.venues.screenshot

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.android.ide.common.rendering.api.SessionParams
import com.manuelblanco.mobilechallenge.core.designsystem.theme.TicketsTheme
import com.manuelblanco.mobilechallenge.core.testing.data.firstVenue
import com.manuelblanco.mobilechallenge.feature.venues.composables.VenueDetailScreen
import com.manuelblanco.mobilechallenge.feature.venues.presentation.VenueDetailContract
import org.junit.Rule
import org.junit.Test

/**
 * Created by Manuel Blanco Murillo on 15/2/24.
 */

class VenueDetailScreenPaparazziTests {

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
                VenueDetailScreen(
                    venueId = "1",
                    venueTitle = "Camela",
                    venue = firstVenue,
                    stateUi = VenueDetailContract.State(
                        venue = firstVenue,
                        isInit = false,
                        isLoading = false,
                        isError = false
                    ),
                    effect = null,
                    onSendEvent = {},
                    onGetVenue = {},
                    onNavigationRequested = {}
                )
            }
        }
    }

    @Test
    fun venueDetailScreen_LoadingDetail() {
        paparazzi.snapshot {
            TicketsTheme {
                VenueDetailScreen(
                    venueId = "1",
                    venueTitle = "Camela",
                    venue = firstVenue,
                    stateUi = VenueDetailContract.State(
                        venue = firstVenue,
                        isInit = false,
                        isLoading = true,
                        isError = false
                    ),
                    effect = null,
                    onSendEvent = {},
                    onGetVenue = {},
                    onNavigationRequested = {}
                )
            }
        }
    }

}
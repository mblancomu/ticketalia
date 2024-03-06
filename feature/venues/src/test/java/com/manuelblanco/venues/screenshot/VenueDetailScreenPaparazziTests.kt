package com.manuelblanco.venues.screenshot

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.android.ide.common.rendering.api.SessionParams
import com.manuelblanco.mobilechallenge.core.designsystem.theme.TicketsTheme
import com.manuelblanco.mobilechallenge.core.testing.data.firstVenue
import com.manuelblanco.mobilechallenge.feature.venues.presentation.composables.VenueDetailScreen
import com.manuelblanco.mobilechallenge.feature.venues.presentation.contracts.VenueDetailContract
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
                    venueTitle = "Camela",
                    stateUi = VenueDetailContract.State(
                        venue = firstVenue,
                        isLoading = false,
                        isError = false
                    ),
                    effect = null,
                    onSendEvent = {},
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
                    venueTitle = "Camela",
                    stateUi = VenueDetailContract.State(
                        venue = firstVenue,
                        isLoading = true,
                        isError = false
                    ),
                    effect = null,
                    onSendEvent = {},
                    onNavigationRequested = {}
                )
            }
        }
    }

}
package com.manuelblanco.venues.screenshot

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.paging.compose.collectAsLazyPagingItems
import com.manuelblanco.mobilechallenge.core.designsystem.theme.TicketsTheme
import com.manuelblanco.mobilechallenge.core.testing.data.pagingVenues
import com.manuelblanco.mobilechallenge.core.testing.data.pagingVenuesEmpty
import com.manuelblanco.mobilechallenge.core.testing.utils.captureMultiDevice
import com.manuelblanco.mobilechallenge.feature.venues.composables.VenuesScreen
import com.manuelblanco.mobilechallenge.feature.venues.presentation.VenuesContract
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import org.robolectric.annotation.LooperMode

/**
 * Created by Manuel Blanco Murillo on 15/2/24.
 */

/*
@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(application = HiltTestApplication::class)
@LooperMode(LooperMode.Mode.PAUSED)
class VenuesScreenRoborazziTests {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun venuesScreen_Roborazzi_ListPopulated() {
        composeTestRule.captureMultiDevice("VenuesScreen_Roborazzi_ListPopulated") {
            TicketsTheme {
                VenuesScreen(
                    venues = flowOf(pagingVenues).collectAsLazyPagingItems(),
                    stateUi = VenuesContract.State(isLoading = false, isError = false)

                ) {}
            }
        }
    }

    @Test
    fun venuesScreen_Roborazzi_EmptyList() {
        composeTestRule.captureMultiDevice("VenuesScreen_Roborazzi_EmptyList") {
            TicketsTheme {
                VenuesScreen(
                    venues = flowOf(pagingVenuesEmpty).collectAsLazyPagingItems(),
                    stateUi = VenuesContract.State(isLoading = false, isError = false)

                ) {}
            }
        }
    }

    @Test
    fun venuesScreen_Roborazzi_ShowError() {
        composeTestRule.captureMultiDevice("VenuesScreen_Roborazzi_ShowError") {
            TicketsTheme {
                VenuesScreen(
                    venues = flowOf(pagingVenuesEmpty).collectAsLazyPagingItems(),
                    stateUi = VenuesContract.State(isLoading = false, isError = true)

                ) {}
            }
        }
    }

    @Test
    fun venuesScreen_Roborazzi_isLoading() {
        composeTestRule.captureMultiDevice("VenuesScreen_Roborazzi_isLoading") {
            TicketsTheme {
                VenuesScreen(
                    venues = flowOf(pagingVenuesEmpty).collectAsLazyPagingItems(),
                    stateUi = VenuesContract.State(isLoading = true, isError = false)

                ) {}
            }
        }
    }

}*/

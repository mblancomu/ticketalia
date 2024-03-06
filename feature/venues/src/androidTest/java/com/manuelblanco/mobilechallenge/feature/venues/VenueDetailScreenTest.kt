package com.manuelblanco.mobilechallenge.feature.venues

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsNotFocused
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.manuelblanco.mobilechallenge.core.testing.data.venueDetail
import com.manuelblanco.mobilechallenge.core.testing.data.venueDetailWithoutInfo
import com.manuelblanco.mobilechallenge.core.testing.data.venueDetailWithoutLocation
import com.manuelblanco.mobilechallenge.feature.venues.presentation.composables.VenueDetailScreen
import com.manuelblanco.mobilechallenge.feature.venues.presentation.contracts.VenueDetailContract
import kotlinx.coroutines.flow.flow
import org.junit.Rule
import org.junit.Test

/**
 * Created by Manuel Blanco Murillo on 15/2/24.
 */
class VenueDetailScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val infoButtonMatcher by lazy {
        hasText(
            composeTestRule.activity.resources.getString(R.string.button_info),
        )
    }

    private val locationButtonMatcher by lazy {
        hasText(
            composeTestRule.activity.resources.getString(R.string.button_location),
        )
    }

    @Test
    fun loading_whenStartTheScreen_showProgress() {
        composeTestRule.setContent {
            VenueDetailScreen(
                venueTitle = venueDetail.data.name,
                stateUi = VenueDetailContract.State(
                    venue = null,
                    isLoading = true,
                    isError = false
                ),
                effect = flow {},
                onSendEvent = {},
                onNavigationRequested = {}
            )
        }

        composeTestRule
            .onNodeWithContentDescription(
                "Progress",
            )
            .assertExists()
    }

    @Test
    fun snackBar_whenExistAnError_showSnackBarMessage() {
        composeTestRule.setContent {
            VenueDetailScreen(
                venueTitle = venueDetail.data.name,
                stateUi = VenueDetailContract.State(
                    venue = null,
                    isLoading = false,
                    isError = true
                ),
                effect = flow {},
                onSendEvent = {},
                onNavigationRequested = {}
            )
        }

        composeTestRule
            .onNodeWithContentDescription(
                "SnackBar",
            )
            .assertExists()

        composeTestRule.onNodeWithText(
            composeTestRule.activity.resources.getString(R.string.global_error)
        )
            .assertExists()
    }

    @Test
    fun showDetailContent_whenFinishTheDownload() {
        composeTestRule.setContent {
            VenueDetailScreen(
                venueTitle = venueDetail.data.name,
                stateUi = VenueDetailContract.State(
                    venue = venueDetail.data,
                    isLoading = false,
                    isError = false
                ),
                effect = flow {},
                onSendEvent = {},
                onNavigationRequested = {}
            )
        }

        composeTestRule
            .onNodeWithContentDescription(
                "Header Detail",
            )
            .assertExists()

        composeTestRule
            .onNodeWithContentDescription(
                "Button Location",
            )
            .assertExists()

        composeTestRule
            .onNodeWithContentDescription(
                "Button Info",
            )
            .assertExists()
    }

    @Test
    fun clickOnButtons_whenExistButtonsAndAreNotFocused_clickOnIt() {
        composeTestRule.setContent {
            VenueDetailScreen(
                venueTitle = venueDetail.data.name,
                stateUi = VenueDetailContract.State(
                    venue = venueDetail.data,
                    isLoading = false,
                    isError = false
                ),
                effect = flow {},
                onSendEvent = {},
                onNavigationRequested = {}
            )
        }

        //If the buttons exist and arent focused, we will be clicked

        composeTestRule
            .onNode(infoButtonMatcher)
            .assertExists()
            .assertIsNotFocused()
            .assertHasClickAction()

        composeTestRule
            .onNode(locationButtonMatcher)
            .assertExists()
            .assertIsNotFocused()
            .assertHasClickAction()
    }

    @Test
    fun noButtonLocation_whenButtonLocationNotExist_shouldNotAppearInTheScreen() {
        composeTestRule.setContent {
            VenueDetailScreen(
                venueTitle = venueDetailWithoutLocation.data.name,
                stateUi = VenueDetailContract.State(
                    venue = venueDetailWithoutLocation.data,
                    isLoading = false,
                    isError = false
                ),
                effect = flow {},
                onSendEvent = {},
                onNavigationRequested = {}
            )
        }

        composeTestRule
            .onNode(locationButtonMatcher)
            .assertDoesNotExist()
    }

    @Test
    fun noButtonInfo_whenButtonInfoNotExist_shouldNotAppearInTheScreen() {
        composeTestRule.setContent {
            VenueDetailScreen(
                venueTitle = venueDetailWithoutInfo.data.name,
                stateUi = VenueDetailContract.State(
                    venue = venueDetailWithoutInfo.data,
                    isLoading = false,
                    isError = false
                ),
                effect = flow {},
                onSendEvent = {},
                onNavigationRequested = {}
            )
        }

        composeTestRule
            .onNode(infoButtonMatcher)
            .assertDoesNotExist()
    }

    @Test
    fun backButton_whenClickBackButton_returnPreviousScreen() {
        composeTestRule.setContent {
            VenueDetailScreen(
                venueTitle = venueDetail.data.name,
                stateUi = VenueDetailContract.State(
                    venue = venueDetail.data,
                    isLoading = false,
                    isError = false
                ),
                effect = flow {},
                onSendEvent = {},
                onNavigationRequested = {}
            )
        }

        composeTestRule
            .onNodeWithContentDescription(
                "Back Button",
            )
            .assertExists()
            .performClick()
    }

}
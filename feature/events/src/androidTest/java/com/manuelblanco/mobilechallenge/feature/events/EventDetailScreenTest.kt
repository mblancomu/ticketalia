package com.manuelblanco.mobilechallenge.feature.events

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsNotFocused
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.manuelblanco.mobilechallenge.core.testing.data.eventDetail
import com.manuelblanco.mobilechallenge.core.testing.data.eventDetailWithoutLocation
import com.manuelblanco.mobilechallenge.core.testing.data.eventDetailWithoutUrl
import com.manuelblanco.mobilechallenge.feature.events.presentation.composables.EventDetailScreen
import com.manuelblanco.mobilechallenge.feature.events.presentation.contracts.EventDetailContract
import kotlinx.coroutines.flow.flow
import org.junit.Rule
import org.junit.Test

/**
 * Created by Manuel Blanco Murillo on 15/2/24.
 */

class EventDetailScreenTest {

    @get:Rule
    var composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val getTicketsButtonMatcher by lazy {
        hasText(
            composeTestRule.activity.resources.getString(R.string.button_tickets),
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
            EventDetailScreen(
                eventTitle = eventDetail.data.name,
                stateUi = EventDetailContract.State(
                    event = null,
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
            EventDetailScreen(
                eventTitle = eventDetail.data.name,
                stateUi = EventDetailContract.State(
                    event = null,
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
            EventDetailScreen(
                eventTitle = eventDetail.data.name,
                stateUi = EventDetailContract.State(
                    event = eventDetail.data,
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
                "Button Get Tickets",
            )
            .assertExists()
    }

    @Test
    fun clickOnButtons_whenExistButtonsAndAreNotFocused_clickOnIt() {
        composeTestRule.setContent {
            EventDetailScreen(
                eventTitle = eventDetail.data.name,
                stateUi = EventDetailContract.State(
                    event = eventDetail.data,
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
            .onNode(getTicketsButtonMatcher)
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
            EventDetailScreen(
                eventTitle = eventDetailWithoutLocation.data.name,
                stateUi = EventDetailContract.State(
                    event = eventDetailWithoutLocation.data,
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
    fun noButtonGetTickets_whenButtonGetTicketsNotExist_shouldNotAppearInTheScreen() {
        composeTestRule.setContent {
            EventDetailScreen(
                eventTitle = eventDetailWithoutUrl.data.name,
                stateUi = EventDetailContract.State(
                    event = eventDetailWithoutUrl.data,
                    isLoading = false,
                    isError = false
                ),
                effect = flow {},
                onSendEvent = {},
                onNavigationRequested = {}
            )
        }

        composeTestRule
            .onNode(getTicketsButtonMatcher)
            .assertDoesNotExist()
    }

    @Test
    fun backButton_whenClickBackButton_returnPreviousScreen() {
        composeTestRule.setContent {
            EventDetailScreen(
                eventTitle = eventDetailWithoutUrl.data.name,
                stateUi = EventDetailContract.State(
                    event = eventDetailWithoutUrl.data,
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
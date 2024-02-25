package com.manuelblanco.mobilechallenge.feature.events

import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.hasScrollToNodeAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollToNode
import com.manuelblanco.mobilechallenge.core.testing.data.eventsFromCacheList
import com.manuelblanco.mobilechallenge.core.testing.data.pagingVenues
import com.manuelblanco.mobilechallenge.feature.events.composables.EventsScreen
import com.manuelblanco.mobilechallenge.feature.events.presentation.EventsContract
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test

/**
 * Created by Manuel Blanco Murillo on 15/2/24.
 */

class EventsScreenTest {

    @get:Rule
    var composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun loading_showsShimmerEffect() {
        composeTestRule.setContent {
            EventsScreen(
                stateUi = EventsContract.State(
                    events = emptyList(),
                    isError = false,
                    isRefreshing = false,
                    isLoading = true
                ),
                effect = flow {},
                onSendEvent = {},
                onNavigationRequested = {}
            )
        }

        composeTestRule
            .onNodeWithContentDescription(
                "Shimmer effect",
            )
            .assertExists()
    }

    @Test
    fun refreshing_whenScrollDownShowPullRefresh() {
        composeTestRule.setContent {
            EventsScreen(
                stateUi = EventsContract.State(
                    events = emptyList(),
                    isError = false,
                    isRefreshing = true,
                    isLoading = true
                ),
                effect = flow {},
                onSendEvent = {},
                onNavigationRequested = {}
            )
        }

        composeTestRule
            .onNodeWithContentDescription(
                "Pull Refresh",
            )
            .assertExists()
    }

    @Test
    fun snackBar_whenExistAnErrorShowSnackBarMessage() {
        composeTestRule.setContent {
            EventsScreen(
                stateUi = EventsContract.State(
                    events = emptyList(),
                    isError = true,
                    isRefreshing = false,
                    isLoading = false
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
        fun showListOfVenues_whenFinishTheDownload() {
            composeTestRule.setContent {
                EventsScreen(
                    stateUi = EventsContract.State(
                        events = eventsFromCacheList,
                        isError = false,
                        isRefreshing = false,
                        isLoading = false
                    ),
                    effect = flow {},
                    onSendEvent = {},
                    onNavigationRequested = {}
                )
            }

            composeTestRule
                .onAllNodes(hasScrollToNodeAction())
                .onFirst()

            composeTestRule
                .onNodeWithText(
                    eventsFromCacheList[2].name,
                    substring = true,
                )
                .assertExists()
                .assertHasClickAction()

            composeTestRule.onNode(hasScrollToNodeAction())
                .performScrollToNode(
                    hasText(
                        eventsFromCacheList[3].name,
                        substring = true,
                    ),
                )

            composeTestRule
                .onNodeWithText(
                    eventsFromCacheList[3].name,
                    substring = true,
                )
                .assertExists()
                .assertHasClickAction()
        }

}
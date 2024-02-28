package com.manuelblanco.mobilechallenge.feature.events

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.hasScrollToNodeAction
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.manuelblanco.mobilechallenge.core.model.data.Cities
import com.manuelblanco.mobilechallenge.core.model.data.EventsFilter
import com.manuelblanco.mobilechallenge.core.model.data.SortType
import com.manuelblanco.mobilechallenge.core.testing.data.eventsFromCacheList
import com.manuelblanco.mobilechallenge.feature.events.composables.EventsScreen
import com.manuelblanco.mobilechallenge.feature.events.presentation.EventsContract
import kotlinx.coroutines.flow.flow
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
                    filters = EventsFilter(
                        sortType = SortType.NONE,
                        city = Cities.ALL.city
                    ),
                    keyword = "",
                    isError = false,
                    isRefreshing = false,
                    isSearching = false,
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
                    filters = EventsFilter(
                        sortType = SortType.NONE,
                        city = Cities.ALL.city
                    ),
                    keyword = "",
                    isError = false,
                    isRefreshing = true,
                    isSearching = false,
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
                    filters = EventsFilter(
                        sortType = SortType.NONE,
                        city = Cities.ALL.city
                    ),
                    keyword = "",
                    isError = true,
                    isRefreshing = false,
                    isSearching = false,
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
    fun showListOfEvents_whenFinishTheDownload() {
        composeTestRule.setContent {
            EventsScreen(
                stateUi = EventsContract.State(
                    events = eventsFromCacheList,
                    filters = EventsFilter(
                        sortType = SortType.NONE,
                        city = Cities.ALL.city
                    ),
                    keyword = "",
                    isError = false,
                    isRefreshing = false,
                    isSearching = false,
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
                eventsFromCacheList[1].name,
                substring = true,
            )
            .assertExists()
            .assertHasClickAction()

        composeTestRule
            .onNodeWithText(
                eventsFromCacheList[2].name,
                substring = true,
            )
            .assertExists()
            .assertHasClickAction()
    }

    @Test
    fun filterOptions_whenClickInFilterIcon_showFilterOptions() {
        composeTestRule.setContent {
            EventsScreen(
                stateUi = EventsContract.State(
                    events = emptyList(),
                    filters = EventsFilter(
                        sortType = SortType.NONE,
                        city = Cities.ALL.city
                    ),
                    keyword = "",
                    isError = false,
                    isRefreshing = false,
                    isSearching = false,
                    isLoading = false
                ),
                effect = flow {},
                onSendEvent = {},
                onNavigationRequested = {}
            )
        }

        composeTestRule
            .onNodeWithContentDescription(
                "Icon Button Search Bar",
            )
            .assertExists()


        composeTestRule
            .onNodeWithContentDescription(
                "Icon Filter"
            )
            .assertExists()
            .performClick()

        composeTestRule
            .onNodeWithContentDescription(
                "Modal Bottom Filters"
            )
            .assertExists()

        composeTestRule
            .onNodeWithContentDescription(
                "Apply Button"
            )
            .assertExists()
            .performClick()

        composeTestRule
            .onNodeWithContentDescription(
                "Modal Bottom Filters"
            )
            .assertDoesNotExist()
    }

}
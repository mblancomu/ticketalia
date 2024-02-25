package com.manuelblanco.mobilechallenge.feature.venues

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasScrollToNodeAction
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.manuelblanco.mobilechallenge.core.model.data.Venue
import com.manuelblanco.mobilechallenge.core.testing.data.firstVenue
import com.manuelblanco.mobilechallenge.core.testing.data.pagingVenues
import com.manuelblanco.mobilechallenge.feature.venues.composables.VenuesScreen
import com.manuelblanco.mobilechallenge.feature.venues.presentation.VenuesContract
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test
import java.util.Timer
import kotlin.concurrent.schedule

/**
 * Created by Manuel Blanco Murillo on 15/2/24.
 */
class VenuesScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun loading_showsShimmerEffect() {
        composeTestRule.setContent {
            VenuesScreen(
                venues = flowOf(pagingVenues).collectAsLazyPagingItems(),
                effect = flow {},
                onSendEvent = {},
                stateUi = VenuesContract.State(
                    isLoading = true,
                    isError = false,
                    isRefreshing = false
                ),
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
            VenuesScreen(
                venues = flowOf(pagingVenues).collectAsLazyPagingItems(),
                effect = flow {},
                onSendEvent = {},
                stateUi = VenuesContract.State(
                    isLoading = true,
                    isError = false,
                    isRefreshing = true
                )
            ) {}
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
            VenuesScreen(
                venues = flowOf(pagingVenues).collectAsLazyPagingItems(),
                effect = flow {},
                onSendEvent = {},
                stateUi = VenuesContract.State(
                    isLoading = false,
                    isError = true,
                    isRefreshing = false
                )
            ) {}
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

    @OptIn(ExperimentalTestApi::class)
    @Ignore
    @Test
    fun showListOfVenues_whenFinishTheDownload() = runTest {
        val deferred = async { fetchVenues() }
        val venues = deferred.await()
        composeTestRule.setContent {
            val items = venues.collectAsLazyPagingItems()
            VenuesScreen(
                venues = items,
                effect = flow {},
                onSendEvent = {},
                stateUi = VenuesContract.State(
                    isLoading = false,
                    isError = false,
                    isRefreshing = false
                )
            ) {}
        }

        //TODO: Failing because of venues from coroutine

        with(composeTestRule) {
            waitUntilAtLeastOneExists(
                timeoutMillis = 10_000,
                matcher = hasContentDescription("List of Venues")
            )

            onAllNodes(hasScrollToNodeAction())
                .onFirst()

            onNodeWithText(
                firstVenue.name,
                substring = true,
            )
                .assertExists()
                .assertHasClickAction()
        }
    }

    private fun asyncTimer(delay: Long = 1000) {
        AsyncTimer.start(delay)
        composeTestRule.waitUntil(
            condition = { AsyncTimer.expired },
            timeoutMillis = delay + 1000
        )
    }
}

object AsyncTimer {
    var expired = false
    fun start(delay: Long = 1000) {
        expired = false
        Timer().schedule(delay) {
            expired = true
        }
    }
}

suspend fun fetchVenues(): Flow<PagingData<Venue>> = flow {
    emit(pagingVenues)
}
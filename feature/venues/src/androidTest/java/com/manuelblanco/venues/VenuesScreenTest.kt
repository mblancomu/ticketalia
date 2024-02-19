package com.manuelblanco.venues

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.hasScrollToNodeAction
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onFirst
import androidx.paging.compose.collectAsLazyPagingItems
import com.manuelblanco.mobilechallenge.core.testing.data.pagingVenues
import com.manuelblanco.mobilechallenge.feature.venues.composables.VenuesScreen
import com.manuelblanco.mobilechallenge.feature.venues.presentation.VenuesContract
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test

/**
 * Created by Manuel Blanco Murillo on 15/2/24.
 */
class VenuesScreenTest {

    @get:Rule(order = 0)
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun showListOfVenues_whenFinishTheDownload(){
        composeTestRule.setContent {
            VenuesScreen(
                venues = flowOf(pagingVenues).collectAsLazyPagingItems(),
                stateUi = VenuesContract.State(isLoading = false, isError = false)
            ) {}
        }

        composeTestRule
            .onAllNodes(hasScrollToNodeAction())
            .onFirst()
    }

}
package com.manuelblanco.ui

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.manuelblanco.mobilechallenge.core.testing.data.pagingVenues
import com.manuelblanco.mobilechallenge.core.ui.components.TicketsTopBar
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class TopBarScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun searchBar_OnTopBar() {

        composeTestRule.activity.setContent {
            TicketsTopBar(isCentered = true)
        }

    }
}
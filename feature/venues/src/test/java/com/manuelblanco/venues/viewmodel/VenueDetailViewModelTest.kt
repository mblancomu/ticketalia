package com.manuelblanco.venues.viewmodel

import com.manuelblanco.mobilechallenge.core.common.result.Result
import com.manuelblanco.mobilechallenge.core.common.result.asResult
import com.manuelblanco.mobilechallenge.core.testing.data.pagingVenues
import com.manuelblanco.mobilechallenge.core.testing.repository.viewmodel.TestVenuesRepository
import com.manuelblanco.mobilechallenge.core.testing.utils.MainCoroutineRule
import com.manuelblanco.mobilechallenge.feature.venues.presentation.VenueDetailContract
import com.manuelblanco.mobilechallenge.feature.venues.presentation.VenueDetailViewModel
import com.manuelblanco.mobilechallenge.feature.venues.usecases.GetVenueUseCaseImpl
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Created by Manuel Blanco Murillo on 13/2/24.
 */
@DelicateCoroutinesApi
@OptIn(ExperimentalCoroutinesApi::class)
class VenueDetailViewModelTest {

    @ExperimentalCoroutinesApi
    private val testDispatcher = StandardTestDispatcher()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val venuesRepository = TestVenuesRepository()
    private val getVenueUseCase = GetVenueUseCaseImpl(venuesRepository)
    private lateinit var viewModel: VenueDetailViewModel

    @Before
    fun setup() {
        viewModel = VenueDetailViewModel(getVenueUseCase)
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `GIVEN a initial state for a Venue WHEN change the UI state should be INITIAL`() = runTest {
        assertEquals(
            VenueDetailContract.State(
                venue = null,
                isLoading = false,
                isError = false
            ),
            viewModel.viewState.value,
        )
    }

    @Test
    fun `GIVEN a response success for a Venue WHEN change the UI state should be SUCCESS`() =
        runTest {
            val collectJob =
                launch(UnconfinedTestDispatcher()) { viewModel.viewState.collect() }

            venuesRepository.senRemoteVenues(pagingVenues)

            val venuesResult =
                venuesRepository.getVenue(id = "1").asResult().filter { it is Result.Success }
                    .first()

            assertTrue(venuesResult is Result.Success)

            collectJob.cancel()
        }
}
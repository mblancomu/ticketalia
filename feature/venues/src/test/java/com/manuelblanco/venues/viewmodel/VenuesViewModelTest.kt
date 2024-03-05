package com.manuelblanco.venues.viewmodel

import com.manuelblanco.mobilechallenge.core.common.result.Result
import com.manuelblanco.mobilechallenge.core.common.result.asResult
import com.manuelblanco.mobilechallenge.core.testing.data.pagingVenues
import com.manuelblanco.mobilechallenge.core.testing.repository.viewmodel.TestVenuesRepository
import com.manuelblanco.mobilechallenge.core.testing.utils.MainCoroutineRule
import com.manuelblanco.mobilechallenge.feature.venues.presentation.contracts.VenuesContract
import com.manuelblanco.mobilechallenge.feature.venues.presentation.viewmodels.VenuesViewModel
import com.manuelblanco.mobilechallenge.core.domain.usecase.GetVenuesUseCaseImpl
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
class VenuesViewModelTest {

    @ExperimentalCoroutinesApi
    private val testDispatcher = StandardTestDispatcher()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val venuesRepository = TestVenuesRepository()
    private val getVenuesUseCase = GetVenuesUseCaseImpl(venuesRepository)
    private lateinit var viewModel: VenuesViewModel

    @Before
    fun setup() {
        viewModel = VenuesViewModel(getVenuesUseCase)
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `GIVEN a initial state for Venues WHEN change the UI state should be LOADING`() = runTest {
        assertEquals(
            VenuesContract.State(
                isLoading = true,
                isRefreshing = false,
                isError = false
            ),
            viewModel.viewState.value,
        )
    }

    @Test
    fun `GIVEN a response success for Venues WHEN change the UI state should be SUCCESS`() =
        runTest {
            val collectJob =
                launch(UnconfinedTestDispatcher()) { viewModel.viewState.collect() }

            venuesRepository.senRemoteVenues(pagingVenues)

            val venuesResult =
                venuesRepository.getVenues().asResult().filter { it is Result.Success }.first()

            assertTrue(venuesResult is Result.Success)

            collectJob.cancel()
        }
}
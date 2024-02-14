package com.manuelblanco.venues

import com.manuelblanco.mobilechallenge.core.common.result.Result
import com.manuelblanco.mobilechallenge.core.common.result.asResult
import com.manuelblanco.mobilechallenge.core.data.repository.VenuesRepository
import com.manuelblanco.mobilechallenge.core.domain.GetVenuesUseCase
import com.manuelblanco.mobilechallenge.core.testing.data.pagingVenues
import com.manuelblanco.mobilechallenge.core.testing.repository.viewmodel.TestVenuesRepository
import com.manuelblanco.mobilechallenge.core.testing.utils.MainCoroutineRule
import com.manuelblanco.mobilechallenge.core.testing.utils.collectDataForTest
import com.manuelblanco.mobilechallenge.feature.venues.presentation.VenuesContract
import com.manuelblanco.mobilechallenge.feature.venues.presentation.VenuesViewModel
import com.manuelblanco.mobilechallenge.feature.venues.usecases.GetVenuesUseCaseImpl
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onEach
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
    lateinit var viewModel: VenuesViewModel

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
    fun `When view model initialized then should emit initial view state first`() = runTest {
        // Given
        val expectedInitialViewState = VenuesContract.State(
            isLoading = true,
            isError = false
        )
        val collectJob =
            launch(UnconfinedTestDispatcher()) { viewModel.viewState.collect() }

        getVenuesUseCase()

        assertEquals(
            expectedInitialViewState, viewModel.viewState.value
        )

        collectJob.cancel()
    }

    @Test
    fun `When getVenues called then should emit a view state`() = runTest {
        // Given
        val expectedInitialViewState = VenuesContract.State(
            isLoading = false,
            isError = false
        )
        val collectJob =
            launch(UnconfinedTestDispatcher()) { viewModel.viewState.collect() }

        getVenuesUseCase()

        assertEquals(
            expectedInitialViewState, viewModel.viewState.value
        )

        collectJob.cancel()
    }
}
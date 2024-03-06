package com.manuelblanco.mobilechallenge.feature.events.viewmodel

import com.manuelblanco.mobilechallenge.core.common.result.Result
import com.manuelblanco.mobilechallenge.core.testing.data.eventsFromCacheList
import com.manuelblanco.mobilechallenge.core.testing.repository.viewmodel.TestEventsRepository
import com.manuelblanco.mobilechallenge.core.testing.utils.MainCoroutineRule
import com.manuelblanco.mobilechallenge.feature.events.presentation.contracts.EventDetailContract
import com.manuelblanco.mobilechallenge.feature.events.presentation.viewmodels.EventDetailViewModel
import com.manuelblanco.mobilechallenge.core.domain.usecase.GetEventFromCacheUseCaseImpl
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
 * Created by Manuel Blanco Murillo on 14/2/24.
 */
class EventDetailViewModelTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @ExperimentalCoroutinesApi
    private val testDispatcher = StandardTestDispatcher()

    private val eventsRepository = TestEventsRepository()
    private val getEventFromCacheUseCase = GetEventFromCacheUseCaseImpl(eventsRepository)

    private lateinit var viewModel: EventDetailViewModel

    @Before
    fun setUp() {
        viewModel = EventDetailViewModel(getEventFromCacheUseCase)
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `GIVEN a initial state for an Event WHEN change the UI state should be INITIAL STATE`() = runTest {
        assertEquals(
            EventDetailContract.State(
                event = null,
                isLoading = false,
                isError = false
            ),
            viewModel.viewState.value,
        )
    }

    @Test
    fun `GIVEN a response success for an Event WHEN change the UI state should be SUCCESS`() =
        runTest {
            val collectJob =
                launch(UnconfinedTestDispatcher()) { viewModel.viewState.collect() }

            eventsRepository.sendCacheEvents(eventsFromCacheList)

            val eventResult =
                eventsRepository.getEventFromCache(id = "1").filter { it is Result.Success }.first()

            assertTrue(eventResult is Result.Success)

            collectJob.cancel()
        }
}
package com.manuelblanco.mobilechallenge.feature.events.viewmodel

import com.manuelblanco.mobilechallenge.core.common.result.Result
import com.manuelblanco.mobilechallenge.core.common.result.asResult
import com.manuelblanco.mobilechallenge.core.testing.data.eventPageFromRemote
import com.manuelblanco.mobilechallenge.core.testing.data.eventsFromCacheList
import com.manuelblanco.mobilechallenge.core.testing.repository.viewmodel.TestEventsRepository
import com.manuelblanco.mobilechallenge.core.testing.utils.MainCoroutineRule
import com.manuelblanco.mobilechallenge.feature.events.presentation.EventsContract
import com.manuelblanco.mobilechallenge.feature.events.presentation.EventsViewModel
import com.manuelblanco.mobilechallenge.feature.events.usecases.GetEventsFromCacheUseCaseImpl
import com.manuelblanco.mobilechallenge.feature.events.usecases.GetEventsFromRemoteUseCaseImpl
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
class EventsViewModelTest {

    @ExperimentalCoroutinesApi
    private val testDispatcher = StandardTestDispatcher()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val eventsRepository = TestEventsRepository()
    private val getEventsFromCacheUseCase = GetEventsFromCacheUseCaseImpl(eventsRepository)
    private val getEventsFromRemoteUseCase = GetEventsFromRemoteUseCaseImpl(eventsRepository)

    private lateinit var viewModel: EventsViewModel

    @Before
    fun setUp() {
        viewModel = EventsViewModel(
            getEventsFromRemoteUseCase, getEventsFromCacheUseCase
        )
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `GIVEN a initial state for Events WHEN change the UI state should be INITIAL STATE`() = runTest {
        assertEquals(
            EventsContract.State(
                events = emptyList(),
                isLoading = false,
                isError = false,
                page = 1
            ),
            viewModel.viewState.value,
        )
    }

    @Test
    fun `GIVEN a response success for Events WHEN change the UI state should be SUCCESS`() =
        runTest {
            val collectJob =
                launch(UnconfinedTestDispatcher()) { viewModel.viewState.collect() }

            eventsRepository.sendRemoteEvents(eventPageFromRemote)
            eventsRepository.sendCacheEvents(eventsFromCacheList)

            val eventResult =
                eventsRepository.getEventsFromCache(limit = 0, offset = 4).asResult().filter { it is Result.Success }.first()

            assertTrue(eventResult is Result.Success)

            collectJob.cancel()
        }
}
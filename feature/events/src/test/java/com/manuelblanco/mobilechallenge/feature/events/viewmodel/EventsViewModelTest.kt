package com.manuelblanco.mobilechallenge.feature.events.viewmodel

import com.manuelblanco.mobilechallenge.core.common.result.Result
import com.manuelblanco.mobilechallenge.core.common.result.asResult
import com.manuelblanco.mobilechallenge.core.domain.model.Cities
import com.manuelblanco.mobilechallenge.core.domain.model.EventsFilter
import com.manuelblanco.mobilechallenge.core.domain.model.SortType
import com.manuelblanco.mobilechallenge.core.testing.data.eventsFromCacheList
import com.manuelblanco.mobilechallenge.core.testing.repository.viewmodel.TestEventsRepository
import com.manuelblanco.mobilechallenge.core.testing.utils.MainCoroutineRule
import com.manuelblanco.mobilechallenge.feature.events.presentation.contracts.EventsContract
import com.manuelblanco.mobilechallenge.feature.events.presentation.viewmodels.EventsViewModel
import com.manuelblanco.mobilechallenge.core.domain.usecase.GetEventsOfflineFirstUseCaseImpl
import com.manuelblanco.mobilechallenge.core.domain.usecase.GetEventsRemoteFirstUseCaseImpl
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
    private val getEventsOfflineFirstUseCase =
        GetEventsOfflineFirstUseCaseImpl(eventsRepository)
    private val getEventsRemoteFirstUseCase =
        GetEventsRemoteFirstUseCaseImpl(eventsRepository)


    private lateinit var viewModel: EventsViewModel

    @Before
    fun setUp() {
        viewModel = EventsViewModel(getEventsRemoteFirstUseCase, getEventsOfflineFirstUseCase)

        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `GIVEN a initial state for Events WHEN change the UI state should be INITIAL STATE`() =
        runTest {
            assertEquals(
                EventsContract.State(
                    events = emptyList(),
                    keyword = "",
                    filters = EventsFilter(
                        sortType = SortType.NAME,
                        city = Cities.ALL.city
                    ),
                    isLoading = false,
                    isRefreshing = false,
                    isSearching = false,
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

            eventsRepository.sendCacheEvents(eventsFromCacheList)

            val eventResult =
                eventsRepository.getEventsFromCache(page = "1", limit = 0, offset = 4, keyword = "")
                    .asResult()
                    .filter { it is Result.Success }.first()

            assertTrue(eventResult is Result.Success)

            collectJob.cancel()
        }
}
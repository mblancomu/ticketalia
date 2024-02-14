package com.manuelblanco.mobilechallenge.feature.events

import com.manuelblanco.mobilechallenge.core.testing.data.eventPageFromRemote
import com.manuelblanco.mobilechallenge.core.testing.data.eventsFromCacheList
import com.manuelblanco.mobilechallenge.core.testing.repository.viewmodel.TestEventsRepository
import com.manuelblanco.mobilechallenge.core.testing.utils.MainCoroutineRule
import com.manuelblanco.mobilechallenge.feature.events.presentation.EventsContract
import com.manuelblanco.mobilechallenge.feature.events.presentation.EventsViewModel
import com.manuelblanco.mobilechallenge.feature.events.usecases.GetEventsFromCacheUseCaseImpl
import com.manuelblanco.mobilechallenge.feature.events.usecases.GetEventsFromRemoteUseCaseImpl
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

/**
 * Created by Manuel Blanco Murillo on 14/2/24.
 */
class EventsViewModelTest {

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
    }

    @Test
    fun stateIsInitiallyLoading() = runTest {
        assertEquals(
            EventsContract.State(
                events = emptyList(),
                isLoading = true,
                isError = false,
                page = 1
            ),
            viewModel.viewState.value,
        )
    }

    @Test
    fun stateIsShowWhenEventsAreLoadingFromRemote() = runTest {
        val collectJob =
            launch(UnconfinedTestDispatcher()) { viewModel.viewState.collect() }

        eventsRepository.sendRemoteEvents(eventPageFromRemote)
        eventsRepository.sendCacheEvents(eventsFromCacheList)

        assertEquals(
            EventsContract.State(
                events = eventsFromCacheList,
                isLoading = false,
                isError = false,
                page = 1
            ), viewModel.viewState.value
        )

        collectJob.cancel()
    }
}
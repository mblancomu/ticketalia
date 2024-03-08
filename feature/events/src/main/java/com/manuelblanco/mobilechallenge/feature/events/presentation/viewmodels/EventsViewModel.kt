package com.manuelblanco.mobilechallenge.feature.events.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.manuelblanco.mobilechallenge.core.common.result.Result
import com.manuelblanco.mobilechallenge.core.common.result.asResult
import com.manuelblanco.mobilechallenge.core.data.mediator.PAGE_SIZE
import com.manuelblanco.mobilechallenge.core.domain.model.Cities
import com.manuelblanco.mobilechallenge.core.domain.model.Event
import com.manuelblanco.mobilechallenge.core.domain.model.EventsFilter
import com.manuelblanco.mobilechallenge.core.domain.model.SortType
import com.manuelblanco.mobilechallenge.core.domain.model.sortAndFilterEvents
import com.manuelblanco.mobilechallenge.core.domain.usecase.GetEventsOfflineFirstUseCase
import com.manuelblanco.mobilechallenge.core.domain.usecase.GetEventsRemoteFirstUseCase
import com.manuelblanco.mobilechallenge.core.ui.mvi.TicketsViewModel
import com.manuelblanco.mobilechallenge.feature.events.presentation.contracts.EventsContract
import com.manuelblanco.mobilechallenge.feature.events.presentation.contracts.EventsContract.Effect
import com.manuelblanco.mobilechallenge.feature.events.presentation.contracts.EventsContract.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Manuel Blanco Murillo on 27/6/23.
 */

@HiltViewModel
class EventsViewModel @Inject constructor(
    private val getEventsRemoteFirstUseCase: GetEventsRemoteFirstUseCase,
    private val getEventsOfflineFirstUseCase: GetEventsOfflineFirstUseCase
) : TicketsViewModel<EventsContract.Event, State, Effect>() {

    private var canPaginate by mutableStateOf(false)
    private var currentPage by mutableIntStateOf(1)

    private var eventsJob: Job? = null

    init {
        getEventsOfflineFirst()
    }

    override fun setInitialState() = State(
        events = emptyList(),
        screenState = State.ScreenState.Idle,
        filter = EventsFilter(
            sortType = SortType.NONE,
            city = Cities.ALL.city
        ),
        keyword = "",
        isRefreshing = false,
        isPaginating = false
    )

    override fun handleEvents(event: EventsContract.Event) {
        when (event) {
            is EventsContract.Event.EventSelection -> setEffect {
                Effect.Navigation.ToEvent(
                    event.eventId, event.eventTitle
                )
            }

            is EventsContract.Event.Filter -> {
                sortAndFilter(event.filters)
            }

            is EventsContract.Event.Refresh -> {
                freshEvents(isRefreshing = true, query = "")
            }

            is EventsContract.Event.Search -> {
                freshEvents(isRefreshing = false, query = event.query.trim().lowercase())
            }

            is EventsContract.Event.Paginate -> {
                getEventsOfflineFirst()
            }
        }
    }

    private fun getEventsOfflineFirst() {
        eventsJob?.cancel()
        eventsJob = viewModelScope.launch {
            if (currentPage == 1 || (currentPage > 1 && canPaginate)) {
                delay(1000L)
                setState { copy(screenState = State.ScreenState.Loading) }
                getEventsOfflineFirstUseCase(
                    page = currentPage.toString(),
                    limit = PAGE_SIZE,
                    offset = (PAGE_SIZE * (currentPage - 1)),
                    keyword = viewState.value.keyword
                ).asResult().collect { result ->
                    when (result) {
                        is Result.Error -> {
                            setState {
                                copy(
                                    screenState = State.ScreenState.Error(message = result.exception?.message.toString()),
                                    isRefreshing = false,
                                    isPaginating = false
                                )
                            }
                        }

                        is Result.Loading -> {
                            setState {
                                copy(
                                    screenState = State.ScreenState.Loading,
                                    isPaginating = currentPage > 1
                                )
                            }
                        }

                        is Result.Success -> {
                            if (result.data.isNotEmpty()) {
                                canPaginate = result.data.size == PAGE_SIZE

                                addNewEvents(result.data, viewState.value.events)

                                if (canPaginate)
                                    currentPage++
                            } else {
                                if (viewState.value.events.isEmpty()) {
                                    setState {
                                        copy(
                                            screenState = State.ScreenState.Empty,
                                            isRefreshing = false,
                                            isPaginating = false
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun sortAndFilter(filters: EventsFilter) {
        resetEvents()
        setState { copy(filter = filters) }
        getEventsOfflineFirst()
    }

    private fun freshEvents(isRefreshing: Boolean, query: String) {
        resetEvents()
        setState { copy(isRefreshing = isRefreshing, keyword = query) }
        getEventsFromRemote()
    }

    private fun getEventsFromRemote() {
        viewModelScope.launch {
            val eventsRefreshDeferred = async {
                getEventsRemoteFirstUseCase(
                    page = "1",
                    keyword = viewState.value.keyword,
                    isRefreshing = true
                )
            }
            try {
                awaitAll(eventsRefreshDeferred)
            } finally {
                getEventsOfflineFirst()
            }
        }
    }

    private fun addNewEvents(newEvents: List<Event>, oldEvents: List<Event>) {
        val events = ArrayList(oldEvents)
        events.addAll(newEvents)
        val filterEvents = events.sortAndFilterEvents(viewState.value.filter)
        setState {
            copy(
                events = filterEvents,
                screenState = if (filterEvents.isEmpty()) State.ScreenState.Empty else
                    State.ScreenState.Loading,
                isRefreshing = false,
                isPaginating = false
            )
        }
        setEffect { Effect.DataWasLoaded }
    }

    private fun resetEvents(clearList: Boolean = true) {
        canPaginate = false
        currentPage = 1
        setState {
            copy(
                isPaginating = false,
                events = if (clearList) emptyList() else viewState.value.events,
                filter = EventsFilter(
                    sortType = SortType.NONE,
                    city = Cities.ALL.city
                )
            )
        }
    }
}
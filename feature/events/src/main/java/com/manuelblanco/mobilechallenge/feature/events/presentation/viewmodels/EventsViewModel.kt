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
            sortType = SortType.NAME,
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
                refresh()
            }

            is EventsContract.Event.Search -> {
                search(event.query.trim().lowercase())
            }

            is EventsContract.Event.Paginate -> {
                loadMoreEvents()
            }

            is EventsContract.Event.ClearFilters -> {
                clearFilters()
            }
        }
    }

    private fun getEventsOfflineFirst() {
        eventsJob?.cancel()
        eventsJob = viewModelScope.launch {
            if (currentPage == 1 || canPaginate) {
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
        resetPagination()
        setState { copy(filter = filters) }
        loadMoreEvents()
    }

    private fun search(keyword: String) {
        resetPagination()
        clearEvents()
        setState { copy(keyword = keyword) }
        getEventsFromRemote()
    }

    private fun clearFilters() {
        resetPagination()
        setState {
            copy(
                filter = EventsFilter(
                    sortType = SortType.NAME,
                    city = Cities.ALL.city
                )
            )
        }
        loadMoreEvents()
    }

    private fun refresh() {
        resetPagination()
        clearEvents()
        setState { copy(isRefreshing = true) }
        getEventsFromRemote()
    }

    private fun loadMoreEvents() {
        getEventsOfflineFirst()
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
        setState {
            copy(
                events = events.sortAndFilterEvents(viewState.value.filter),
                isRefreshing = false,
                isPaginating = false
            )
        }
        setEffect { Effect.DataWasLoaded }
    }

    private fun resetPagination() {
        canPaginate = false
        currentPage = 1
        setState { copy(isPaginating = false, ) }
    }

    private fun clearEvents(){
        setState { copy(events = emptyList(), filter = EventsFilter(
            sortType = SortType.NAME,
            city = Cities.ALL.city
        )) }
    }
}
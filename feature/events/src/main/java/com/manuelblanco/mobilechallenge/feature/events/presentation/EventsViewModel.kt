package com.manuelblanco.mobilechallenge.feature.events.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.manuelblanco.mobilechallenge.core.common.result.Result
import com.manuelblanco.mobilechallenge.core.common.result.asResult
import com.manuelblanco.mobilechallenge.core.data.mediator.PAGE_SIZE
import com.manuelblanco.mobilechallenge.core.domain.GetEventsOfflineFirstUseCase
import com.manuelblanco.mobilechallenge.core.domain.GetEventsRemoteFirstUseCase
import com.manuelblanco.mobilechallenge.core.model.data.Event
import com.manuelblanco.mobilechallenge.core.ui.mvi.TicketsViewModel
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
) : TicketsViewModel<EventsContract.Event, EventsContract.State, EventsContract.Effect>() {

    private var canPaginate by mutableStateOf(false)

    private var eventsJob: Job? = null

    init {
        getEventsOfflineFirst()
    }

    override fun setInitialState() = EventsContract.State(
        events = emptyList(),
        isLoading = false,
        isRefreshing = false,
        isError = false,
        page = 1
    )

    override fun handleEvents(event: EventsContract.Event) {
        when (event) {
            is EventsContract.Event.EventSelection -> setEffect {
                EventsContract.Effect.Navigation.ToEvent(
                    event.eventId, event.eventTitle
                )
            }

            is EventsContract.Event.Filter -> {}
            is EventsContract.Event.Refresh -> setEffect { EventsContract.Effect.RefreshingData }
            is EventsContract.Event.Search -> {}
        }
    }

    fun refresh() {
        eventsJob?.cancel()
        canPaginate = false
        setState { copy(page = 1, isRefreshing = true, events = emptyList()) }
        viewModelScope.launch {
            val eventsRefreshDeferred = async { getEventsRemoteFirstUseCase(page = "1", true) }
            try {
                awaitAll(eventsRefreshDeferred)
            } finally {
                getEventsOfflineFirst()
            }
        }
    }

    fun loadMoreEvents() {
        eventsJob?.cancel()
        getEventsOfflineFirst()
    }

    private fun getEventsOfflineFirst() {
        eventsJob = viewModelScope.launch {
            if (viewState.value.page == 1 || (viewState.value.page != 1 && canPaginate)) {
                delay(1000L)
                setState { copy(isLoading = true, isError = false) }
                getEventsOfflineFirstUseCase(
                    page = viewState.value.page.toString(),
                    limit = PAGE_SIZE,
                    offset = (PAGE_SIZE * (viewState.value.page - 1))
                ).asResult().collect { result ->
                    when (result) {
                        is Result.Error -> {
                            setState {
                                copy(
                                    isLoading = false,
                                    isRefreshing = false,
                                    isError = true
                                )
                            }
                        }

                        is Result.Loading -> {
                            setState { copy(isLoading = true, isError = false) }
                        }

                        is Result.Success -> {
                            if (result.data.isNotEmpty()) {
                                canPaginate = result.data.size == PAGE_SIZE

                                addNewEvents(result.data, viewState.value.events)

                                if (canPaginate)
                                    setState { copy(page = viewState.value.page + 1) }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun addNewEvents(newEvents: List<Event>, oldEvents: List<Event>) {
        val events = ArrayList(oldEvents)
        events.addAll(newEvents)
        finishedDownload(events)
    }

    private fun finishedDownload(events: List<Event>) {
        setState { copy(isLoading = false, isRefreshing = false, isError = false, events = events) }
        setEffect { EventsContract.Effect.DataWasLoaded }
    }

}
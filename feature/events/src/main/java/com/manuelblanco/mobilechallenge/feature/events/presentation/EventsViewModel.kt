package com.manuelblanco.mobilechallenge.feature.events.presentation

import androidx.lifecycle.viewModelScope
import com.manuelblanco.mobilechallenge.core.common.result.Result
import com.manuelblanco.mobilechallenge.core.data.mediator.PAGE_SIZE
import com.manuelblanco.mobilechallenge.core.domain.GetEventsFromCacheUseCase
import com.manuelblanco.mobilechallenge.core.domain.GetEventsFromRemoteUseCase
import com.manuelblanco.mobilechallenge.core.model.data.Event
import com.manuelblanco.mobilechallenge.core.ui.mvi.TicketsViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.SerializationException
import javax.inject.Inject

/**
 * Created by Manuel Blanco Murillo on 27/6/23.
 */

@HiltViewModel
class EventsViewModel @Inject constructor(
    private val getEventsFromRemoteUseCase: GetEventsFromRemoteUseCase,
    private val getEventsFromCacheUseCase: GetEventsFromCacheUseCase,
) : TicketsViewModel<EventsContract.Event, EventsContract.State, EventsContract.Effect>() {

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()

    private var eventsJob: Job? = null
    private var totalPages = 1

    init {
        getEvents()
    }

    override fun setInitialState() = EventsContract.State(
        events = emptyList(),
        isLoading = false,
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
            is EventsContract.Event.Refresh -> {}
            is EventsContract.Event.Search -> {}
        }
    }

    fun refresh() {
        _isRefreshing.value = true
        setInitialState()
        getEvents()
    }

    fun getMoreEvents() {
        eventsJob?.cancel()
        if ((totalPages > 1) && (viewState.value.page < totalPages)) {
            setState { copy(page = viewState.value.page + 1) }
            getEvents()
        }
    }

    private fun getEvents() {
        eventsJob = viewModelScope.launch {
            delay(1000L)
            setState { copy(isLoading = true, isError = false) }
            getEventsFromRemoteUseCase(
                page = viewState.value.page.toString(),
            ).collect { result ->
                when (result) {
                    is Result.Error -> {
                        setState { copy(isLoading = false, isError = true) }
                        if (result.exception !is SerializationException) {
                            getEventsFromCacheUseCase(
                                PAGE_SIZE,
                                (PAGE_SIZE * (viewState.value.page - 1))
                            ).collect { events ->
                                if (events.isNotEmpty()) {
                                    finishedDownload(events)

                                    _isRefreshing.emit(false)
                                }
                            }
                        }
                    }

                    is Result.Loading -> {
                        setState { copy(isLoading = true, isError = false) }
                    }

                    is Result.Success -> {
                        totalPages = result.data.totalPages ?: 41
                        getEventsFromCacheUseCase(
                            PAGE_SIZE,
                            (PAGE_SIZE * (viewState.value.page - 1))
                        ).collect { events ->
                            addNewEvents(events, viewState.value.events)

                            _isRefreshing.emit(false)
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
        setState { copy(isLoading = false, isError = false, events = events) }
        setEffect { EventsContract.Effect.DataWasLoaded }
    }

}
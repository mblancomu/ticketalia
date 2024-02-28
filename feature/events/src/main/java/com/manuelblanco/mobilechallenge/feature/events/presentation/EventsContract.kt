package com.manuelblanco.mobilechallenge.feature.events.presentation

import com.manuelblanco.mobilechallenge.core.model.data.EventsFilter
import com.manuelblanco.mobilechallenge.core.ui.mvi.ViewEvent
import com.manuelblanco.mobilechallenge.core.ui.mvi.ViewSideEffect
import com.manuelblanco.mobilechallenge.core.ui.mvi.ViewState

/**
 * Created by Manuel Blanco Murillo on 31/1/24.
 */
class EventsContract {

    sealed class Event : ViewEvent {
        data object Refresh : Event()
        data object Paginate : Event()
        data object ClearFilters : Event()
        data class Search(val query: String) : Event()
        data class Filter(val filters: EventsFilter) : Event()
        data class EventSelection(val eventId: String, val eventTitle: String) :
            Event()
    }

    data class State(
        val events: List<com.manuelblanco.mobilechallenge.core.model.data.Event>,
        val filters: EventsFilter,
        val keyword: String,
        val isLoading: Boolean,
        val isRefreshing: Boolean,
        val isSearching: Boolean,
        val isError: Boolean,
        val page: Int = 1
    ) : ViewState

    sealed class Effect : ViewSideEffect {
        data object DataWasLoaded : Effect()

        sealed class Navigation : Effect() {
            data class ToEvent(val eventId: String, val eventTitle: String) : Navigation()
        }
    }

}
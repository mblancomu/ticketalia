package com.manuelblanco.mobilechallenge.feature.events.presentation.contracts

import com.manuelblanco.mobilechallenge.core.domain.model.EventsFilter
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
        data class Search(val query: String) : Event()
        data class Filter(val filters: EventsFilter) : Event()
        data class EventSelection(val eventId: String, val eventTitle: String) :
            Event()
    }

    data class State(
        val events: List<com.manuelblanco.mobilechallenge.core.domain.model.Event>,
        val screenState: ScreenState,
        val filter: EventsFilter,
        val keyword: String,
        val isPaginating: Boolean,
        val isRefreshing: Boolean
    ) : ViewState {
        sealed interface ScreenState {
            data object Loading : ScreenState
            data object Idle : ScreenState
            data object Empty : ScreenState
            data class Error(val message: String) : ScreenState
        }
    }

    sealed class Effect : ViewSideEffect {
        data object DataWasLoaded : Effect()

        sealed class Navigation : Effect() {
            data class ToEvent(val eventId: String, val eventTitle: String) : Navigation()
        }
    }

}
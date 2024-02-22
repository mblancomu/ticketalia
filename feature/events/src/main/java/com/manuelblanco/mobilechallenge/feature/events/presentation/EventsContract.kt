package com.manuelblanco.mobilechallenge.feature.events.presentation

import com.manuelblanco.mobilechallenge.core.ui.mvi.ViewEvent
import com.manuelblanco.mobilechallenge.core.ui.mvi.ViewSideEffect
import com.manuelblanco.mobilechallenge.core.ui.mvi.ViewState

/**
 * Created by Manuel Blanco Murillo on 31/1/24.
 */
class EventsContract {

    sealed class Event : ViewEvent {
        data object Refresh : Event()
        data object Search : Event()
        data object Filter : Event()
        data class EventSelection(val eventId: String, val eventTitle: String) :
            Event()
    }

    data class State(
        val events: List<com.manuelblanco.mobilechallenge.core.model.data.Event>,
        val isLoading: Boolean,
        val isRefreshing: Boolean,
        val isError: Boolean,
        val page: Int = 1
    ) : ViewState

    sealed class Effect : ViewSideEffect {
        data object DataWasLoaded : Effect()
        data object RefreshingData : Effect()

        sealed class Navigation : Effect() {
            data class ToEvent(val eventId: String, val eventTitle: String) : Navigation()
        }
    }

}
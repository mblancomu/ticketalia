package com.manuelblanco.mobilechallenge.feature.venues.presentation

import com.manuelblanco.mobilechallenge.core.ui.mvi.ViewEvent
import com.manuelblanco.mobilechallenge.core.ui.mvi.ViewSideEffect
import com.manuelblanco.mobilechallenge.core.ui.mvi.ViewState

/**
 * Created by Manuel Blanco Murillo on 31/1/24.
 */
class VenuesContract {

    sealed class Event : ViewEvent {
        data class Search(val query: String) : Event()
        data object Filter : Event()
        data object Refresh : Event()
        data class VenueSelection(val venueId: String, val venueTitle: String) :
            Event()
    }

    data class State(
        val isLoading: Boolean,
        val isRefreshing: Boolean,
        val isError: Boolean
    ) : ViewState

    sealed class Effect : ViewSideEffect {
        data object DataWasLoaded : Effect()

        sealed class Navigation : Effect() {
            data class ToVenue(val venueId: String, val venueTitle: String) : Navigation()
        }
    }

}
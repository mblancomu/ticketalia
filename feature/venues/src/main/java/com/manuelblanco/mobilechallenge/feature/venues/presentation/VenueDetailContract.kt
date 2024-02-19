package com.manuelblanco.mobilechallenge.feature.venues.presentation

import com.manuelblanco.mobilechallenge.core.model.data.Venue
import com.manuelblanco.mobilechallenge.core.ui.mvi.ViewEvent
import com.manuelblanco.mobilechallenge.core.ui.mvi.ViewSideEffect
import com.manuelblanco.mobilechallenge.core.ui.mvi.ViewState

/**
 * Created by Manuel Blanco Murillo on 31/1/24.
 */
class VenueDetailContract {

    sealed class Event : ViewEvent {
        object LinkButtonClicked : Event()
        object BackButtonClicked : Event()
        object DirectionButtonClicked : Event()
    }

    data class State(
        val venue: Venue?,
        val isLoading: Boolean,
        val isError: Boolean
    ) : ViewState

    sealed class Effect : ViewSideEffect {
        sealed class Navigation : Effect() {
            object Back : Navigation()
            object Localization : Navigation()
            object Info : Navigation()
        }
    }

}
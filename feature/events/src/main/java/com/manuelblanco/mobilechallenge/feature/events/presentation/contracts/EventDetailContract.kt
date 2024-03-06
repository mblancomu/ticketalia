package com.manuelblanco.mobilechallenge.feature.events.presentation.contracts

import com.manuelblanco.mobilechallenge.core.ui.mvi.ViewEvent
import com.manuelblanco.mobilechallenge.core.ui.mvi.ViewSideEffect
import com.manuelblanco.mobilechallenge.core.ui.mvi.ViewState

/**
 * Created by Manuel Blanco Murillo on 31/1/24.
 */
class EventDetailContract {

    sealed class Event : ViewEvent {
        object Link : Event()
        object GoBack : Event()
        object Direction : Event()
    }

    data class State(
        val event: com.manuelblanco.mobilechallenge.core.domain.model.Event?,
        val isLoading: Boolean,
        val isError: Boolean,
    ) : ViewState

    sealed class Effect : ViewSideEffect {
        sealed class Navigation : Effect() {
            object Back : Navigation()
            object Tickets : Navigation()
            object Localization : Navigation()
        }
    }

}
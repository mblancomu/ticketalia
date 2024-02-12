package com.manuelblanco.mobilechallenge.feature.venues.presentation

import androidx.paging.PagingData
import com.manuelblanco.mobilechallenge.core.model.data.Venue
import com.manuelblanco.mobilechallenge.core.ui.mvi.ViewEvent
import com.manuelblanco.mobilechallenge.core.ui.mvi.ViewSideEffect
import com.manuelblanco.mobilechallenge.core.ui.mvi.ViewState
import kotlinx.coroutines.flow.Flow

/**
 * Created by Manuel Blanco Murillo on 31/1/24.
 */
class VenuesContract {

    sealed class Event : ViewEvent {
        object Search : Event()
        object Filter : Event()
        data class VenueSelection(val venueId: String, val venueTitle: String) :
            Event()
    }

    data class State(
        val isLoading: Boolean,
        val isError: Boolean
    ) : ViewState

    sealed class Effect : ViewSideEffect {
        object DataWasLoaded : Effect()

        sealed class Navigation : Effect() {
            data class ToVenue(val venueId: String, val venueTitle: String) : Navigation()
        }
    }

}
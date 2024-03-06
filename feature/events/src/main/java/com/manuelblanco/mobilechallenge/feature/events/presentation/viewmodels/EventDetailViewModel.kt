package com.manuelblanco.mobilechallenge.feature.events.presentation.viewmodels

import androidx.lifecycle.viewModelScope
import com.manuelblanco.mobilechallenge.core.common.result.Result
import com.manuelblanco.mobilechallenge.core.domain.usecase.GetEventFromCacheUseCase
import com.manuelblanco.mobilechallenge.core.ui.mvi.TicketsViewModel
import com.manuelblanco.mobilechallenge.feature.events.presentation.contracts.EventDetailContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Manuel Blanco Murillo on 27/6/23.
 */

@HiltViewModel
class EventDetailViewModel @Inject constructor(
    private val getEventFromCacheUseCase: GetEventFromCacheUseCase,
) : TicketsViewModel<EventDetailContract.Event, EventDetailContract.State, EventDetailContract.Effect>() {

    override fun setInitialState() = EventDetailContract.State(
        event = null,
        isLoading = false,
        isError = false
    )

    override fun handleEvents(event: EventDetailContract.Event) {
        when (event) {
            is EventDetailContract.Event.Direction -> {
                setEffect { EventDetailContract.Effect.Navigation.Localization }
            }

            is EventDetailContract.Event.GoBack -> {
                setEffect { EventDetailContract.Effect.Navigation.Back }
            }

            is EventDetailContract.Event.Link -> {
                setEffect { EventDetailContract.Effect.Navigation.Tickets }
            }
        }
    }

    fun getEvent(id: String) {
        viewModelScope.launch {
            setState { copy(isLoading = true, isError = false) }
            getEventFromCacheUseCase(
                id,
            ).collect { result ->
                when (result) {
                    is Result.Error -> {
                        setState { copy(isLoading = false, isError = true) }
                    }

                    is Result.Loading -> {
                        setState { copy(isLoading = true, isError = false) }
                    }

                    is Result.Success -> {
                        setState { copy(isLoading = false, isError = false, event = result.data) }
                    }
                }
            }
        }
    }
}
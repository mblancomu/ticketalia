package com.manuelblanco.mobilechallenge.feature.events.presentation

import androidx.lifecycle.viewModelScope
import com.manuelblanco.mobilechallenge.core.common.result.Result
import com.manuelblanco.mobilechallenge.core.domain.GetEventFromCacheUseCase
import com.manuelblanco.mobilechallenge.core.ui.mvi.TicketsViewModel
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

    init {
        setState { copy(isInit = true, isLoading = false, isError = false) }
    }

    override fun setInitialState() = EventDetailContract.State(
        event = null,
        isInit = false,
        isLoading = false,
        isError = false
    )

    override fun handleEvents(event: EventDetailContract.Event) {
        when (event) {
            is EventDetailContract.Event.Direction -> {}
            is EventDetailContract.Event.GoBack -> {
                setEffect { EventDetailContract.Effect.Navigation.Back }
            }

            is EventDetailContract.Event.Link -> {}
        }
    }

    fun getEvent(id: String) {
        viewModelScope.launch {
            setState { copy(isInit = false, isLoading = true, isError = false) }
            getEventFromCacheUseCase(
                id,
            ).collect { result ->
                when (result) {
                    is Result.Error -> {
                        setState { copy(isInit = false, isLoading = false, isError = true) }
                    }

                    is Result.Loading -> {
                        setState { copy(isInit = false, isLoading = true, isError = false) }
                    }

                    is Result.Success -> {
                        setState { copy(isInit = false, isLoading = false, isError = false, event = result.data) }
                    }
                }
            }
        }
    }
}
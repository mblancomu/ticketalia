package com.manuelblanco.mobilechallenge.feature.venues.presentation

import androidx.lifecycle.viewModelScope
import com.manuelblanco.mobilechallenge.core.common.result.Result
import com.manuelblanco.mobilechallenge.core.domain.GetVenueUseCase
import com.manuelblanco.mobilechallenge.core.ui.mvi.TicketsViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Manuel Blanco Murillo on 27/6/23.
 */

@HiltViewModel
class VenueDetailViewModel @Inject constructor(
    private val getVenueUseCase: GetVenueUseCase,
) : TicketsViewModel<VenueDetailContract.Event, VenueDetailContract.State, VenueDetailContract.Effect>() {

    init {
        setState { copy(isInit = true, isLoading = false, isError = false) }
    }

    override fun setInitialState(): VenueDetailContract.State = VenueDetailContract.State(
        venue = null,
        isInit = false,
        isLoading = false,
        isError = false
    )

    override fun handleEvents(event: VenueDetailContract.Event) {
        when (event) {
            is VenueDetailContract.Event.DirectionButtonClicked -> {}
            is VenueDetailContract.Event.LinkButtonClicked -> {}
            is VenueDetailContract.Event.BackButtonClicked -> {
                setEffect { VenueDetailContract.Effect.Navigation.Back }
            }
        }
    }

    fun getVenue(venueId: String) {
        viewModelScope.launch {
            setState { copy(isInit = false, isLoading = true, isError = false) }
            getVenueUseCase(venueId).collect { result ->
                when (result) {
                    is Result.Error -> {
                        setState { copy(isInit = false, isLoading = false, isError = true) }
                    }

                    is Result.Loading -> {
                        setState { copy(isInit = false, isLoading = true, isError = false) }
                    }

                    is Result.Success -> {
                        setState {
                            copy(
                                isInit = false,
                                isLoading = false,
                                isError = false,
                                venue = result.data
                            )
                        }
                    }
                }
            }
        }
    }
}
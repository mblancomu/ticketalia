package com.manuelblanco.mobilechallenge.feature.venues.presentation

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.manuelblanco.mobilechallenge.core.common.result.Result
import com.manuelblanco.mobilechallenge.core.common.result.asResult
import com.manuelblanco.mobilechallenge.core.domain.GetVenuesUseCase
import com.manuelblanco.mobilechallenge.core.model.data.Venue
import com.manuelblanco.mobilechallenge.core.ui.mvi.TicketsViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Manuel Blanco Murillo on 27/6/23.
 */

@HiltViewModel
class VenuesViewModel @Inject constructor(
    private val getVenuesUseCase: GetVenuesUseCase,
) : TicketsViewModel<VenuesContract.Event, VenuesContract.State, VenuesContract.Effect>() {

    private val _venues = MutableStateFlow<PagingData<Venue>>(PagingData.empty())
    val venues: StateFlow<PagingData<Venue>> = _venues

    init {
        getVenues()
    }

    override fun setInitialState(): VenuesContract.State = VenuesContract.State(
        isLoading = false,
        isRefreshing = false,
        isError = false
    )

    override fun handleEvents(event: VenuesContract.Event) {
        when (event) {
            is VenuesContract.Event.VenueSelection -> setEffect {
                VenuesContract.Effect.Navigation.ToVenue(
                    event.venueId, event.venueTitle
                )
            }

            is VenuesContract.Event.Filter -> {}
            is VenuesContract.Event.Search -> {}
            is VenuesContract.Event.Refresh -> setEffect { VenuesContract.Effect.RefreshingData }
        }
    }

    fun getVenues() {
        viewModelScope.launch {
            setState { copy(isLoading = true, isError = false) }
            getVenuesUseCase().cachedIn(viewModelScope).asResult().collect { result ->
                when (result) {
                    is Result.Error -> {
                        setState { copy(isLoading = false, isError = true) }
                    }

                    is Result.Loading -> {
                        setState { copy(isLoading = true, isError = false) }
                    }

                    is Result.Success -> {
                        delay(1000L)
                        _venues.value = result.data
                        setState { copy(isLoading = false, isError = false, isRefreshing = false) }
                        setEffect { VenuesContract.Effect.DataWasLoaded }
                    }
                }
            }
        }
    }

    fun refreshStateUi(){
        setState { copy(isRefreshing = true) }
        _venues.value = PagingData.empty()
    }

}
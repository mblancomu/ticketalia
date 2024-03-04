package com.manuelblanco.mobilechallenge.feature.events.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manuelblanco.mobilechallenge.core.domain.GetEventsFilterUseCase
import com.manuelblanco.mobilechallenge.core.domain.SetEventsFilterUseCase
import com.manuelblanco.mobilechallenge.core.model.data.Cities
import com.manuelblanco.mobilechallenge.core.model.data.EventsFilter
import com.manuelblanco.mobilechallenge.core.model.data.SortType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Manuel Blanco Murillo on 29/2/24.
 */
@HiltViewModel
class EventsFilterViewModel @Inject constructor(
    private val getEventsFilterUseCase: GetEventsFilterUseCase,
    private val setEventsFilterUseCase: SetEventsFilterUseCase
) : ViewModel() {

    private var _filters: MutableStateFlow<EventsFilter> =
        MutableStateFlow(EventsFilter(sortType = SortType.NAME, city = Cities.ALL.city))
    val filters: StateFlow<EventsFilter> = _filters

    init {
        getEventsFilter()
    }

    fun setEventsFilter(filters: EventsFilter) {
        viewModelScope.launch {
            setEventsFilterUseCase(filters)
        }
    }

    private fun getEventsFilter() {
        viewModelScope.launch {
            getEventsFilterUseCase().collect {
                _filters.value = it
            }
        }
    }

    fun onSelectedSortItem(sortType: SortType) {
        _filters.value = _filters.value.copy(sortType = sortType)
    }

    fun onSelectedFilterItem(filter: String) {
        _filters.value = _filters.value.copy(city = filter)
    }

}
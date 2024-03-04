package com.manuelblanco.mobilechallenge.core.domain

import com.manuelblanco.mobilechallenge.core.common.result.Result
import com.manuelblanco.mobilechallenge.core.model.data.Event
import com.manuelblanco.mobilechallenge.core.model.data.EventsFilter
import kotlinx.coroutines.flow.Flow

/**
 * Created by Manuel Blanco Murillo on 29/2/24.
 */
interface SetEventsFilterUseCase {
    suspend operator fun invoke(filters: EventsFilter)
}
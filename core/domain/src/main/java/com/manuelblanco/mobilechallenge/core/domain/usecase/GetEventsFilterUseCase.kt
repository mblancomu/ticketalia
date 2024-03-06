package com.manuelblanco.mobilechallenge.core.domain.usecase

import com.manuelblanco.mobilechallenge.core.domain.model.EventsFilter
import kotlinx.coroutines.flow.Flow

/**
 * Created by Manuel Blanco Murillo on 29/2/24.
 */
interface GetEventsFilterUseCase {
    operator fun invoke(): Flow<EventsFilter>
}
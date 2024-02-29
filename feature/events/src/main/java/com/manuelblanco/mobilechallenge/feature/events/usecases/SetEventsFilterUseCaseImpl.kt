package com.manuelblanco.mobilechallenge.feature.events.usecases

import com.manuelblanco.mobilechallenge.core.data.repository.EventsRepository
import com.manuelblanco.mobilechallenge.core.domain.SetEventsFilterUseCase
import com.manuelblanco.mobilechallenge.core.model.data.EventsFilter
import javax.inject.Inject

/**
 * Created by Manuel Blanco Murillo on 29/2/24.
 */
class SetEventsFilterUseCaseImpl @Inject constructor(
    private val eventsRepository: EventsRepository
) : SetEventsFilterUseCase {
    override suspend fun invoke(filters: EventsFilter) =
        eventsRepository.setEventsFilter(filters)
}
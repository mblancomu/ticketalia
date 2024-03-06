package com.manuelblanco.mobilechallenge.core.domain.usecase

import com.manuelblanco.mobilechallenge.core.domain.repository.EventsRepository
import com.manuelblanco.mobilechallenge.core.domain.usecase.SetEventsFilterUseCase
import com.manuelblanco.mobilechallenge.core.domain.model.EventsFilter
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
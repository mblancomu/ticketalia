package com.manuelblanco.mobilechallenge.core.domain.usecase

import com.manuelblanco.mobilechallenge.core.domain.repository.EventsRepository
import com.manuelblanco.mobilechallenge.core.domain.usecase.GetEventsFilterUseCase
import com.manuelblanco.mobilechallenge.core.domain.model.EventsFilter
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Manuel Blanco Murillo on 29/2/24.
 */
class GetEventsFilterUseCaseImpl @Inject constructor(
    private val eventsRepository: EventsRepository
) : GetEventsFilterUseCase {
    override fun invoke(): Flow<EventsFilter> =
        eventsRepository.getEventsFilter()
}
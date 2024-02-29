package com.manuelblanco.mobilechallenge.feature.events.usecases

import com.manuelblanco.mobilechallenge.core.data.repository.EventsRepository
import com.manuelblanco.mobilechallenge.core.domain.GetEventsFilterUseCase
import com.manuelblanco.mobilechallenge.core.model.data.EventsFilter
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
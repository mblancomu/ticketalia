package com.manuelblanco.mobilechallenge.feature.events.usecases

import com.manuelblanco.mobilechallenge.core.data.repository.EventsRepository
import com.manuelblanco.mobilechallenge.core.domain.GetEventsFromCacheUseCase
import com.manuelblanco.mobilechallenge.core.model.data.Event
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Manuel Blanco Murillo on 31/1/24.
 */
class GetEventsFromCacheUseCaseImpl @Inject constructor(
    private val eventsRepository: EventsRepository
) : GetEventsFromCacheUseCase {
    override fun invoke(limit: Int, offset: Int): Flow<List<Event>> =
        eventsRepository.getEventsFromCache(limit, offset)

}
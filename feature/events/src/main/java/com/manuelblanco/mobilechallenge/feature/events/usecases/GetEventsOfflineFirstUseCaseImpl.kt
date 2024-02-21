package com.manuelblanco.mobilechallenge.feature.events.usecases

import com.manuelblanco.mobilechallenge.core.data.repository.EventsOfflineFirstRepository
import com.manuelblanco.mobilechallenge.core.domain.GetEventsOfflineFirstUseCase
import com.manuelblanco.mobilechallenge.core.model.data.Event
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Manuel Blanco Murillo on 31/1/24.
 */
class GetEventsOfflineFirstUseCaseImpl @Inject constructor(
    private val eventsRepository: EventsOfflineFirstRepository
) : GetEventsOfflineFirstUseCase {
    override fun invoke(
        page: String,
        limit: Int,
        offset: Int
    ): Flow<List<Event>> =
        eventsRepository.getEventsFromCache(page, limit, offset)

}
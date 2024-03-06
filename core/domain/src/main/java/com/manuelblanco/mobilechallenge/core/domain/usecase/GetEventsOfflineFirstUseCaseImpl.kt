package com.manuelblanco.mobilechallenge.core.domain.usecase

import com.manuelblanco.mobilechallenge.core.domain.repository.EventsRepository
import com.manuelblanco.mobilechallenge.core.domain.usecase.GetEventsOfflineFirstUseCase
import com.manuelblanco.mobilechallenge.core.domain.model.Event
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Manuel Blanco Murillo on 31/1/24.
 */
class GetEventsOfflineFirstUseCaseImpl @Inject constructor(
    private val eventsRepository: EventsRepository
) : GetEventsOfflineFirstUseCase {
    override fun invoke(
        page: String,
        limit: Int,
        offset: Int,
        keyword: String
    ): Flow<List<Event>> =
        eventsRepository.getEventsFromCache(page, limit, offset, keyword)

}
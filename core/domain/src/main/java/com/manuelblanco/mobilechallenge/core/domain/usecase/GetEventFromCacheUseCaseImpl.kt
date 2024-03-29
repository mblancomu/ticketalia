package com.manuelblanco.mobilechallenge.core.domain.usecase;

import com.manuelblanco.mobilechallenge.core.common.result.Result
import com.manuelblanco.mobilechallenge.core.domain.repository.EventsRepository
import com.manuelblanco.mobilechallenge.core.domain.usecase.GetEventFromCacheUseCase
import com.manuelblanco.mobilechallenge.core.domain.model.Event
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject;

/**
 * Created by Manuel Blanco Murillo on 7/2/24.
 */

class GetEventFromCacheUseCaseImpl @Inject constructor(
    private val eventsRepository: EventsRepository
) : GetEventFromCacheUseCase {
    override fun invoke(id: String): Flow<Result<Event>> =
        eventsRepository.getEventFromCache(id)
}
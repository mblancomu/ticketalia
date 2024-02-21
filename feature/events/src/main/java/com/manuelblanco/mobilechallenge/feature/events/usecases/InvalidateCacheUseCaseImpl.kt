package com.manuelblanco.mobilechallenge.feature.events.usecases

import com.manuelblanco.mobilechallenge.core.data.repository.EventsOfflineFirstRepository
import com.manuelblanco.mobilechallenge.core.domain.InvalidateCacheUseCase
import javax.inject.Inject

/**
 * Created by Manuel Blanco Murillo on 21/2/24.
 */
class InvalidateCacheUseCaseImpl @Inject constructor(
    private val eventsRepository: EventsOfflineFirstRepository
) : InvalidateCacheUseCase {
    override suspend fun invoke() = eventsRepository.invalidateCache()

}
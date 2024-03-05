package com.manuelblanco.mobilechallenge.core.domain.usecase

import com.manuelblanco.mobilechallenge.core.domain.repository.EventsRepository
import com.manuelblanco.mobilechallenge.core.domain.usecase.GetEventsRemoteFirstUseCase
import javax.inject.Inject

/**
 * Created by Manuel Blanco Murillo on 31/1/24.
 */
class GetEventsRemoteFirstUseCaseImpl @Inject constructor(
    private val eventsRepository: EventsRepository
) : GetEventsRemoteFirstUseCase {
    override suspend fun invoke(page: String, keyword: String, isRefreshing: Boolean) =
        eventsRepository.getEventsFromRemote(page, keyword, isRefreshing)

}
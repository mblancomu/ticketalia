package com.manuelblanco.mobilechallenge.feature.events.usecases

import com.manuelblanco.mobilechallenge.core.common.result.Result
import com.manuelblanco.mobilechallenge.core.data.repository.EventsRepository
import com.manuelblanco.mobilechallenge.core.domain.GetEventsFromRemoteUseCase
import com.manuelblanco.mobilechallenge.core.model.data.Page
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Manuel Blanco Murillo on 31/1/24.
 */
class GetEventsFromRemoteUseCaseImpl @Inject constructor(
    private val eventsRepository: EventsRepository
) : GetEventsFromRemoteUseCase {
    override fun invoke(page: String): Flow<Result<Page>> =
        eventsRepository.getEventsFromRemote(page)

}
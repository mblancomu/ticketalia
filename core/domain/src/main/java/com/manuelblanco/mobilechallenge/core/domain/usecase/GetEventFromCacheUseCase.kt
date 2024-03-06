package com.manuelblanco.mobilechallenge.core.domain.usecase

import com.manuelblanco.mobilechallenge.core.common.result.Result
import com.manuelblanco.mobilechallenge.core.domain.model.Event
import kotlinx.coroutines.flow.Flow

interface GetEventFromCacheUseCase {
    operator fun invoke(id: String): Flow<Result<Event>>
}
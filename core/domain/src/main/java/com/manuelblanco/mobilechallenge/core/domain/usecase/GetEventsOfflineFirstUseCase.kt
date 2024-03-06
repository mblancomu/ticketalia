package com.manuelblanco.mobilechallenge.core.domain.usecase

import com.manuelblanco.mobilechallenge.core.domain.model.Event
import kotlinx.coroutines.flow.Flow

interface GetEventsOfflineFirstUseCase {
    operator fun invoke(page: String, limit: Int, offset: Int, keyword: String): Flow<List<Event>>
}
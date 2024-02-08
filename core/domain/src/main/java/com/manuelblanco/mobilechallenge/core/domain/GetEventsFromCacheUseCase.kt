package com.manuelblanco.mobilechallenge.core.domain

import com.manuelblanco.mobilechallenge.core.model.data.Event
import kotlinx.coroutines.flow.Flow

interface GetEventsFromCacheUseCase {
    operator fun invoke(limit: Int, offset: Int): Flow<List<Event>>
}
package com.manuelblanco.mobilechallenge.core.domain

import com.manuelblanco.mobilechallenge.core.model.data.Event
import kotlinx.coroutines.flow.Flow

interface GetEventsOfflineFirstUseCase {
    operator fun invoke(page: String, limit: Int, offset: Int, keyword: String): Flow<List<Event>>
}
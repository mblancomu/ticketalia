package com.manuelblanco.mobilechallenge.core.data.repository

import com.manuelblanco.mobilechallenge.core.common.result.Result
import com.manuelblanco.mobilechallenge.core.model.data.Event
import kotlinx.coroutines.flow.Flow

interface EventsRepository {
    suspend fun getEventsFromRemote(page: String, isRefreshing: Boolean)
    fun getEventsFromCache(page: String, limit: Int, offset: Int): Flow<List<Event>>
    fun getEventFromCache(id: String): Flow<Result<Event>>
}
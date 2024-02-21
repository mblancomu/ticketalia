package com.manuelblanco.mobilechallenge.core.data.repository

import com.manuelblanco.mobilechallenge.core.common.result.Result
import com.manuelblanco.mobilechallenge.core.model.data.Event
import com.manuelblanco.mobilechallenge.core.model.data.Page
import kotlinx.coroutines.flow.Flow

interface EventsOfflineFirstRepository {
    suspend fun getEventsFromRemote(page: String)
    fun getEventsFromCache(page: String, limit: Int, offset: Int): Flow<List<Event>>
    suspend fun invalidateCache()
}
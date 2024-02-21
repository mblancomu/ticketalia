package com.manuelblanco.mobilechallenge.core.testing.repository.viewmodel

import com.manuelblanco.mobilechallenge.core.data.repository.EventsOfflineFirstRepository
import com.manuelblanco.mobilechallenge.core.model.data.Event
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

/**
 * Created by Manuel Blanco Murillo on 14/2/24.
 */
class TestEventsOfflineFirstRepository : EventsOfflineFirstRepository {

    private val eventsFromCacheFlow: MutableSharedFlow<List<Event>> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    fun sendCacheEvents(events: List<Event>) {
        eventsFromCacheFlow.tryEmit(events)
    }

    override suspend fun getEventsFromRemote(page: String) {
        TODO("Not yet implemented")
    }

    override fun getEventsFromCache(page: String, limit: Int, offset: Int): Flow<List<Event>> =
        eventsFromCacheFlow

    override suspend fun invalidateCache() {
        TODO("Not yet implemented")
    }
}
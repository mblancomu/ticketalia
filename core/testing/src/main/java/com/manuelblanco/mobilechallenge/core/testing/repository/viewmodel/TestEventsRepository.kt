package com.manuelblanco.mobilechallenge.core.testing.repository.viewmodel

import com.manuelblanco.mobilechallenge.core.common.result.Result
import com.manuelblanco.mobilechallenge.core.common.result.asResult
import com.manuelblanco.mobilechallenge.core.data.repository.EventsRepository
import com.manuelblanco.mobilechallenge.core.model.data.Event
import com.manuelblanco.mobilechallenge.core.model.data.EventsFilter
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.map

/**
 * Created by Manuel Blanco Murillo on 14/2/24.
 */
class TestEventsRepository : EventsRepository {

    private val eventsFromCacheFlow: MutableSharedFlow<List<Event>> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    private val eventsFilter: MutableSharedFlow<EventsFilter> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    fun sendCacheEvents(events: List<Event>) {
        eventsFromCacheFlow.tryEmit(events)
    }

    override suspend fun getEventsFromRemote(
        page: String,
        keyword: String,
        isRefreshing: Boolean
    ) {
    }

    override fun getEventsFromCache(
        page: String,
        limit: Int,
        offset: Int,
        keyword: String
    ): Flow<List<Event>> =
        eventsFromCacheFlow

    override fun getEventFromCache(id: String): Flow<Result<Event>> =
        eventsFromCacheFlow.map { events -> events.find { it.id == id }!! }.asResult()

    override fun getEventsFilter(): Flow<EventsFilter> = eventsFilter

    override suspend fun setEventsFilter(filters: EventsFilter) {}
}
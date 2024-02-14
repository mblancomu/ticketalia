package com.manuelblanco.mobilechallenge.core.testing.repository.viewmodel

import com.manuelblanco.mobilechallenge.core.common.result.Result
import com.manuelblanco.mobilechallenge.core.common.result.asResult
import com.manuelblanco.mobilechallenge.core.data.repository.EventsRepository
import com.manuelblanco.mobilechallenge.core.model.data.Event
import com.manuelblanco.mobilechallenge.core.model.data.Page
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.map

/**
 * Created by Manuel Blanco Murillo on 14/2/24.
 */
class TestEventsRepository : EventsRepository {

    private val eventsFromRemoteFlow: MutableSharedFlow<Result<Page>> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    private val eventsFromCacheFlow: MutableSharedFlow<List<Event>> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    fun sendRemoteEvents(page: Result<Page>){
        eventsFromRemoteFlow.tryEmit(page)
    }

    fun sendCacheEvents(events: List<Event>){
        eventsFromCacheFlow.tryEmit(events)
    }

    override fun getEventsFromRemote(page: String): Flow<Result<Page>> = eventsFromRemoteFlow

    override fun getEventsFromCache(limit: Int, offset: Int): Flow<List<Event>> =
        eventsFromCacheFlow

    override fun getEventFromCache(id: String): Flow<Result<Event>> =
        eventsFromCacheFlow.map { events -> events.find { it.id == id }!! }.asResult()

    override fun getEventsFromRemoteByRadius(
        radius: String,
        unitRadius: String,
        page: String
    ): Flow<Result<List<Event>>> {
        TODO("Not yet implemented")
    }

    override fun getEventsFromRemoteByType(type: String, page: String): Flow<Result<List<Event>>> {
        TODO("Not yet implemented")
    }

    override fun getEventsFromRemoteByCity(city: String, page: String): Flow<Result<List<Event>>> {
        TODO("Not yet implemented")
    }
}
package com.manuelblanco.mobilechallenge.core.testing.repository.usecase

import com.manuelblanco.mobilechallenge.core.data.repository.EventsOfflineFirstRepository
import com.manuelblanco.mobilechallenge.core.model.data.Event
import com.manuelblanco.mobilechallenge.core.testing.data.eventsFromCacheList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

/**
 * Created by Manuel Blanco Murillo on 13/2/24.
 */
class FakeEventsOfflineFirstRepository : EventsOfflineFirstRepository {

    override suspend fun getEventsFromRemote(page: String) {
        TODO("Not yet implemented")
    }

    override fun getEventsFromCache(page: String, limit: Int, offset: Int): Flow<List<Event>> =
        flow {
            if (offset > eventsFromCacheList.size) {
                emit(emptyList())
            } else {
                emit(eventsFromCacheList)
            }
        }.catch { println("Some error") }

    override suspend fun invalidateCache() {
        TODO("Not yet implemented")
    }
}
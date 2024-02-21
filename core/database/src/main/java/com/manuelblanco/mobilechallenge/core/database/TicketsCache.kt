package com.manuelblanco.mobilechallenge.core.database

import com.manuelblanco.mobilechallenge.core.database.dao.EventDao
import com.manuelblanco.mobilechallenge.core.database.model.EventEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Manuel Blanco Murillo on 1/2/24.
 */
class TicketsCache @Inject constructor(
    private val eventsDao: EventDao
) : Cache {
    override suspend fun saveEventsInCache(events: List<EventEntity>) {
        eventsDao.insertAll(events)
    }

    override fun getEventFromCache(eventId: String): Flow<EventEntity> =
        eventsDao.getEventEntity(eventId)

    override fun getEventFromCacheByCity(
        city: String,
        limit: Int,
        offset: Int
    ): Flow<List<EventEntity>> = eventsDao.getEventEntitiesByCity(city, limit, offset)

    override fun getEventFromCacheByType(
        type: String,
        limit: Int,
        offset: Int
    ): Flow<List<EventEntity>> = eventsDao.getEventEntitiesByType(type, limit, offset)

    override fun getEventFromCacheByCountry(
        country: String,
        limit: Int,
        offset: Int
    ): Flow<List<EventEntity>> = eventsDao.getEventEntitiesByCountry(country, limit, offset)


    override fun getEventsFromCache(limit: Int, offset: Int): Flow<List<EventEntity>> =
        eventsDao.getEventEntities(limit, offset)

    override suspend fun invalidateCache() {
        eventsDao.deleteAll()
    }
}
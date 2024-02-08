package com.manuelblanco.mobilechallenge.core.database

import com.manuelblanco.mobilechallenge.core.database.model.EventEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by Manuel Blanco Murillo on 1/2/24.
 */
interface Cache {

    suspend fun saveEventsInCache(events: List<EventEntity>)
    fun getEventFromCache(eventId: String): Flow<EventEntity>
    fun getEventFromCacheByCity(city: String, limit: Int, offset: Int): Flow<List<EventEntity>>
    fun getEventFromCacheByType(type: String, limit: Int, offset: Int): Flow<List<EventEntity>>
    fun getEventFromCacheByCountry(country: String, limit: Int, offset: Int): Flow<List<EventEntity>>
    fun getEventsFromCache(limit: Int, offset: Int): Flow<List<EventEntity>>
}
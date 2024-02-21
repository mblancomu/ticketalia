package com.manuelblanco.mobilechallenge.core.data.repository

import com.manuelblanco.mobilechallenge.core.common.result.Result
import com.manuelblanco.mobilechallenge.core.model.data.Event
import com.manuelblanco.mobilechallenge.core.model.data.Page
import kotlinx.coroutines.flow.Flow

interface EventsRepository {
    fun getEventsFromRemote(page: String, isRefreshing: Boolean): Flow<Result<Page>>
    fun getEventsFromCache(limit: Int, offset: Int): Flow<List<Event>>
    fun getEventFromCache(id: String): Flow<Result<Event>>
    fun getEventsFromRemoteByRadius(
        radius: String,
        unitRadius: String,
        page: String
    ): Flow<Result<List<Event>>>

    fun getEventsFromRemoteByType(
        type: String,
        page: String
    ): Flow<Result<List<Event>>>

    fun getEventsFromRemoteByCity(
        city: String,
        page: String
    ): Flow<Result<List<Event>>>
}
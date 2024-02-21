package com.manuelblanco.mobilechallenge.core.data.repository

import com.manuelblanco.mobilechallenge.core.common.result.Result
import com.manuelblanco.mobilechallenge.core.common.result.asResult
import com.manuelblanco.mobilechallenge.core.data.model.asEventEntities
import com.manuelblanco.mobilechallenge.core.database.TicketsCache
import com.manuelblanco.mobilechallenge.core.database.model.asExternalModel
import com.manuelblanco.mobilechallenge.core.model.data.Event
import com.manuelblanco.mobilechallenge.core.model.data.Page
import com.manuelblanco.mobilechallenge.core.network.model.global.toExternalModel
import com.manuelblanco.mobilechallenge.core.network.retrofit.TicketsRemote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class EventsRepositoryImpl @Inject constructor(
    private val cache: TicketsCache,
    private val api: TicketsRemote
) : EventsRepository {

    //TEMPORARY FIX VALUES
    private val PAGE_SIZE = "20"
    private val SORT_BY = "date,name,desc"
    private val COUNTRY_CODE = "ES"

    override fun getEventsFromRemote(
        page: String,
        isRefreshing: Boolean
    ): Flow<Result<Page>> = flow {
        val response = api.getEvents(
            page = page,
            size = PAGE_SIZE,
            sort = SORT_BY,
            countryCode = COUNTRY_CODE
        )
        cache.saveEventsInCache(
            events = response.asEventEntities()
        )
        emit(response.page.toExternalModel())
    }.asResult()

    override fun getEventsFromCache(limit: Int, offset: Int): Flow<List<Event>> =
        cache.getEventsFromCache(limit, offset)
            .map { event -> event.map { it::asExternalModel.invoke() } }


    override fun getEventFromCache(id: String): Flow<Result<Event>> =
        cache.getEventFromCache(id).map { it.asExternalModel() }.asResult()

    override fun getEventsFromRemoteByRadius(
        radius: String,
        unitRadius: String,
        page: String
    ): Flow<Result<List<Event>>> = flow {
        val response = api.getEventsByRadius(
            page = page,
            size = PAGE_SIZE,
            sort = SORT_BY,
            radius = radius,
            unitRadius = unitRadius
        )

        emit(response.asEventEntities().map { it.asExternalModel() })
    }.asResult()

    override fun getEventsFromRemoteByType(
        type: String,
        page: String
    ): Flow<Result<List<Event>>> = flow {
        val response = api.getEventsByType(
            page = page,
            size = PAGE_SIZE,
            sort = SORT_BY,
            type = type
        )

        emit(response.asEventEntities().map { it.asExternalModel() })
    }.asResult()

    override fun getEventsFromRemoteByCity(
        city: String,
        page: String
    ): Flow<Result<List<Event>>> = flow {
        val response = api.getEventsByCity(
            page = page,
            size = PAGE_SIZE,
            sort = SORT_BY,
            city = city
        )

        emit(response.asEventEntities().map { it.asExternalModel() })
    }.asResult()

}
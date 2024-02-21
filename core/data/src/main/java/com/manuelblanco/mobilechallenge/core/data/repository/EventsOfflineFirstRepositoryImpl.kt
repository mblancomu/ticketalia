package com.manuelblanco.mobilechallenge.core.data.repository

import com.manuelblanco.mobilechallenge.core.data.model.asEventEntities
import com.manuelblanco.mobilechallenge.core.database.TicketsCache
import com.manuelblanco.mobilechallenge.core.database.model.asExternalModel
import com.manuelblanco.mobilechallenge.core.model.data.Event
import com.manuelblanco.mobilechallenge.core.network.retrofit.TicketsRemote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class EventsOfflineFirstRepositoryImpl @Inject constructor(
    private val cache: TicketsCache,
    private val api: TicketsRemote
) : EventsOfflineFirstRepository {

    private val PAGE_SIZE = "20"
    private val SORT_BY = "name,desc"
    private val COUNTRY_CODE = "ES"

    override suspend fun getEventsFromRemote(
        page: String
    ) {
        api.getEvents(
            page = page,
            size = PAGE_SIZE,
            sort = SORT_BY,
            countryCode = COUNTRY_CODE
        ).also {
            cache.saveEventsInCache(events = it.asEventEntities())
        }
    }

    override fun getEventsFromCache(
        page: String,
        limit: Int,
        offset: Int
    ): Flow<List<Event>> =
        cache.getEventsFromCache(limit, offset)
            .map { event -> event.map { it::asExternalModel.invoke() } }.onEach {
                if (it.isEmpty()) {
                    getEventsFromRemote(
                        page
                    )
                }
            }

    override suspend fun invalidateCache() {
        cache.invalidateCache()
    }

}
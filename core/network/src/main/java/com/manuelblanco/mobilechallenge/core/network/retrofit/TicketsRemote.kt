package com.manuelblanco.mobilechallenge.core.network.retrofit

import com.manuelblanco.mobilechallenge.core.network.BuildConfig
import com.manuelblanco.mobilechallenge.core.network.model.event.NetworkEvent
import com.manuelblanco.mobilechallenge.core.network.model.venue.NetworkVenue
import javax.inject.Inject

/**
 * Created by Manuel Blanco Murillo on 1/2/24.
 */
class TicketsRemote @Inject constructor(
    private val ticketsApi: RetrofitNetworkApi
) : Remote {
    override suspend fun getEvents(
        page: String?,
        size: String?,
        sort: String?,
        countryCode: String?
    ): NetworkPageEventsResponse =
        ticketsApi.getEvents(page, size, sort, countryCode, BuildConfig.CONSUMER_KEY)

    override suspend fun getEventsByRadius(
        page: String?,
        radius: String?,
        unitRadius: String?,
        size: String?,
        sort: String?
    ): NetworkPageEventsResponse =
        ticketsApi.getEventsByRadius(page, radius, unitRadius, size, sort, BuildConfig.CONSUMER_KEY)

    override suspend fun getEventsByType(
        page: String?,
        size: String?,
        sort: String?,
        type: String?
    ): NetworkPageEventsResponse =
        ticketsApi.getEventsByType(page, size, sort, type, BuildConfig.CONSUMER_KEY)

    override suspend fun getEventsByCity(
        page: String?,
        size: String?,
        sort: String?,
        city: String?
    ): NetworkPageEventsResponse =
        ticketsApi.getEventsByCity(page, size, sort, city, BuildConfig.CONSUMER_KEY)

    override suspend fun getEventDetail(
        id: String?
    ): NetworkDetailResponse<NetworkEvent> = ticketsApi.getEventDetail(id, BuildConfig.CONSUMER_KEY)

    override suspend fun getVenues(
        page: String?,
        size: String?,
        sort: String?,
        countryCode: String?
    ): NetworkPageVenuesResponse =
        ticketsApi.getVenues(page, size, sort, BuildConfig.CONSUMER_KEY, countryCode)

    override suspend fun getVenueDetail(
        id: String?
    ): NetworkDetailResponse<NetworkVenue> = ticketsApi.getVenueDetail(id, BuildConfig.CONSUMER_KEY)

}
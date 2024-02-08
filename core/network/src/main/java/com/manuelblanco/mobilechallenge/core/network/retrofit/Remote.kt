package com.manuelblanco.mobilechallenge.core.network.retrofit

import com.manuelblanco.mobilechallenge.core.network.model.event.NetworkEvent
import com.manuelblanco.mobilechallenge.core.network.model.venue.NetworkVenue

/**
 * Created by Manuel Blanco Murillo on 1/2/24.
 */
interface Remote {

    suspend fun getEvents(
        page: String?,
        size: String?,
        sort: String?,
        countryCode: String?
    ): NetworkPageEventsResponse

    suspend fun getEventsByRadius(
        page: String?,
        radius: String?,
        unitRadius: String?,
        size: String?,
        sort: String?
    ): NetworkPageEventsResponse

    suspend fun getEventsByType(
        page: String?,
        size: String?,
        sort: String?,
        type: String?
    ): NetworkPageEventsResponse

    suspend fun getEventsByCity(
        page: String?,
        size: String?,
        sort: String?,
        city: String?
    ): NetworkPageEventsResponse

    suspend fun getEventDetail(
        id: String?
    ): NetworkDetailResponse<NetworkEvent>

    suspend fun getVenues(
        page: String?,
        size: String?,
        sort: String?,
        countryCode: String?
    ): NetworkPageVenuesResponse

    suspend fun getVenueDetail(
        id: String?
    ): NetworkDetailResponse<NetworkVenue>
}
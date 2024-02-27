package com.manuelblanco.mobilechallenge.core.network.retrofit

import com.manuelblanco.mobilechallenge.core.network.BuildConfig
import com.manuelblanco.mobilechallenge.core.network.model.event.NetworkEvent
import com.manuelblanco.mobilechallenge.core.network.model.event.NetworkEventList
import com.manuelblanco.mobilechallenge.core.network.model.global.NetworkPage
import com.manuelblanco.mobilechallenge.core.network.model.venue.NetworkVenue
import com.manuelblanco.mobilechallenge.core.network.model.venue.NetworkVenueList
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Manuel Blanco Murillo on 27/6/23.
 */
interface RetrofitNetworkApi {

    //EVENTS ENDPOINTS
    @GET("discovery/v2/events")
    suspend fun getEvents(
        @Query("page") page: String?,
        @Query("size") size: String?,
        @Query("sort") sort: String?,
        @Query("countryCode") countryCode: String?,
        @Query("keyword") keyword: String?,
        @Query("apikey") apikey: String
    ): NetworkPageEventsResponse

    @GET("discovery/v2/events")
    suspend fun getEventsByRadius(
        @Query("page") page: String?,
        @Query("radius") radius: String?,
        @Query("unit") unitRadius: String?,
        @Query("size") size: String?,
        @Query("sort") sort: String?,
        @Query("apikey") apikey: String
    ): NetworkPageEventsResponse

    @GET("discovery/v2/events")
    suspend fun getEventsByType(
        @Query("page") page: String?,
        @Query("size") size: String?,
        @Query("sort") sort: String?,
        @Query("classificationName") type: String?,
        @Query("apikey") apikey: String
    ): NetworkPageEventsResponse

    @GET("discovery/v2/events")
    suspend fun getEventsByCity(
        @Query("page") page: String?,
        @Query("size") size: String?,
        @Query("sort") sort: String?,
        @Query("city") city: String?,
        @Query("apikey") apikey: String
    ): NetworkPageEventsResponse

    @GET("discovery/v2/events/{id}")
    suspend fun getEventDetail(
        @Path("id") id: String?,
        @Query("apikey") apikey: String
    ): NetworkDetailResponse<NetworkEvent>

    //VENUES ENDPOINTS
    @GET("discovery/v2/venues")
    suspend fun getVenues(
        @Query("page") page: String?,
        @Query("size") size: String?,
        @Query("sort") sort: String?,
        @Query("apikey") apikey: String = BuildConfig.CONSUMER_KEY,
        @Query("countryCode") countryCode: String?
    ): NetworkPageVenuesResponse

    @GET("discovery/v2/venues/{id}")
    suspend fun getVenueDetail(
        @Path("id") id: String?,
        @Query("apikey") apikey: String = BuildConfig.CONSUMER_KEY
    ): NetworkDetailResponse<NetworkVenue>

}

@Serializable
data class NetworkDetailResponse<T>(
    val data: T,
)

@Serializable
data class NetworkPageEventsResponse(
    @SerialName("_embedded")
    val items: NetworkEventList? = null,
    val page: NetworkPage? = null
)

@Serializable
data class NetworkPageVenuesResponse(
    @SerialName("_embedded")
    val items: NetworkVenueList? = null,
    val page: NetworkPage? = null
)


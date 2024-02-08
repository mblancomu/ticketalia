package com.manuelblanco.mobilechallenge.core.network.model.event

import com.manuelblanco.mobilechallenge.core.model.data.Event
import com.manuelblanco.mobilechallenge.core.model.data.Location
import com.manuelblanco.mobilechallenge.core.network.model.global.NetworkImages
import com.manuelblanco.mobilechallenge.core.network.model.global.NetworkLocation
import com.manuelblanco.mobilechallenge.core.network.model.global.toExternalModel
import com.manuelblanco.mobilechallenge.core.network.model.venue.NetworkVenueList
import com.manuelblanco.mobilechallenge.core.network.model.venue.toPlace
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by Manuel Blanco Murillo on 27/6/23.
 */

@Serializable
data class NetworkEvent(
    val id: String,
    val name: String? = "",
    val description: String? = "",
    val location: NetworkLocation? = null,
    val images: List<NetworkImages>? = null,
    val dates: NetworkEventDates? = null,
    @SerialName("_embedded")
    val venues: NetworkVenueList? = null,
    val priceRanges: List<NetworkEventPrice>? = emptyList(),
    val place: NetworkEventPlace? = null,
    @SerialName("classifications") val genre: List<NetworkEventClassification>? = emptyList()
)

fun NetworkEvent.toExternalModel() = Event(
    id = id,
    name = name ?: "",
    description = description ?: "",
    location = location?.toExternalModel() ?: Location(latitude = 0.0, longitude = 0.0),
    imageUrl = images?.get(0)?.url ?: "",
    dateTime = dates?.start?.dateTime ?: "",
    place = if (!venues?.venues.isNullOrEmpty()) venues?.venues?.get(0)?.toPlace()
        ?: "" else place?.toExternalPlace() ?: "",
    city = if (!venues?.venues.isNullOrEmpty()) venues?.venues?.get(0)?.city?.name
        ?: "" else place?.city?.name ?: "",
    country = if (!venues?.venues.isNullOrEmpty()) venues?.venues?.get(0)?.country?.name
        ?: "" else place?.country?.name ?: "",
    genres = genre?.get(0)?.genre?.name ?: "",
    prices = priceRanges?.toPriceRange() ?: "",
)

fun List<NetworkEvent>.toExternalModel() = map { it.toExternalModel() }
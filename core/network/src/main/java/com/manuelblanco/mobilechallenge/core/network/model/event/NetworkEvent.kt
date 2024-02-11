package com.manuelblanco.mobilechallenge.core.network.model.event

import com.manuelblanco.mobilechallenge.core.network.model.global.NetworkImages
import com.manuelblanco.mobilechallenge.core.network.model.global.NetworkLocation
import com.manuelblanco.mobilechallenge.core.network.model.venue.NetworkVenueList
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


package com.manuelblanco.mobilechallenge.core.network.model.venue

import com.manuelblanco.mobilechallenge.core.model.data.Location
import com.manuelblanco.mobilechallenge.core.model.data.Venue
import com.manuelblanco.mobilechallenge.core.network.model.global.NetworkAddress
import com.manuelblanco.mobilechallenge.core.network.model.global.NetworkCity
import com.manuelblanco.mobilechallenge.core.network.model.global.NetworkCountry
import com.manuelblanco.mobilechallenge.core.network.model.global.NetworkImages
import com.manuelblanco.mobilechallenge.core.network.model.global.NetworkLocation
import com.manuelblanco.mobilechallenge.core.network.model.global.toExternalModel
import com.manuelblanco.mobilechallenge.core.network.model.global.toFinalAddress
import kotlinx.serialization.Serializable

/**
 * Created by Manuel Blanco Murillo on 27/6/23.
 */

@Serializable
data class NetworkVenue(
    val id: String,
    val name: String? = "",
    val description: String? = "",
    val location: NetworkLocation? = null,
    val images: List<NetworkImages>? = emptyList(),
    val country: NetworkCountry? = null,
    val city: NetworkCity? = null,
    val address: NetworkAddress? = null,
    val distance: Double? = 0.0,
    val units: String? = ""
)

fun NetworkVenue.toExternalModel() = Venue(
    id = id,
    name = name ?: "",
    description = description ?: "",
    location = location?.toExternalModel() ?: Location(0.0, 0.0),
    imageUrl = images?.get(0)?.url ?: "",
    country = country?.name ?: "",
    city = city?.name ?: "",
    address = address?.toFinalAddress() ?: "",
    distance = "$distance"
)

fun List<NetworkVenue>.toExternalModel() = map { it.toExternalModel() }

fun NetworkVenue.toPlace(): String = "$name - $address"
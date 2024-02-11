package com.manuelblanco.mobilechallenge.core.network.model.venue

import com.manuelblanco.mobilechallenge.core.network.model.global.NetworkAddress
import com.manuelblanco.mobilechallenge.core.network.model.global.NetworkCity
import com.manuelblanco.mobilechallenge.core.network.model.global.NetworkCountry
import com.manuelblanco.mobilechallenge.core.network.model.global.NetworkImages
import com.manuelblanco.mobilechallenge.core.network.model.global.NetworkLocation
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
    val units: String? = "",
    val url: String? = ""
)

fun NetworkVenue.toPlace(): String = "$name - $address"
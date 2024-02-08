package com.manuelblanco.mobilechallenge.core.network.model.event

import com.manuelblanco.mobilechallenge.core.network.model.global.NetworkAddress
import com.manuelblanco.mobilechallenge.core.network.model.global.NetworkCity
import com.manuelblanco.mobilechallenge.core.network.model.global.NetworkCountry
import com.manuelblanco.mobilechallenge.core.network.model.global.NetworkLocation
import kotlinx.serialization.Serializable

@Serializable
data class NetworkEventPlace(
    val name: String,
    val city: NetworkCity,
    val address: NetworkAddress,
    val country: NetworkCountry,
    val location: NetworkLocation
)

fun NetworkEventPlace.toExternalPlace(): String {
    return "$name - $address"
}

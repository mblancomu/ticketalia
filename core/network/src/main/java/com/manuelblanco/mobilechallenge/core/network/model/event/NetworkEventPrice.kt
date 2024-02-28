package com.manuelblanco.mobilechallenge.core.network.model.event

import kotlinx.serialization.Serializable

@Serializable
data class NetworkEventPrice(
    val currency: String? = "",
    val min: Double? = 0.0,
    val max: Double? = 0.0
)

fun NetworkEventPrice.toPrice(): String {
    return min.toString()
}

fun NetworkEventPrice.toCurrency(): String {
    return currency ?: ""
}

fun List<NetworkEventPrice?>.toPrice(): String {
    return this.mapNotNull { "${it?.max}-${it?.min} ${it?.currency}" }.joinToString(separator = ",")
}
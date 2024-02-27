package com.manuelblanco.mobilechallenge.core.network.model.event

import kotlinx.serialization.Serializable

@Serializable
data class NetworkEventPrice(
    val currency: String? = "",
    val min: Double? = 0.0,
    val max: Double? = 0.0
)

fun NetworkEventPrice.toPriceRange(): String {
    return "$min $currency"
}

fun List<NetworkEventPrice?>.toPriceRange(): String {
    return this.mapNotNull { "${it?.max}-${it?.min} ${it?.currency}" }.joinToString(separator = ",")
}
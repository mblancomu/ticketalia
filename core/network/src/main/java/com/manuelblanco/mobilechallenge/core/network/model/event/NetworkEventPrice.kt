package com.manuelblanco.mobilechallenge.core.network.model.event

import kotlinx.serialization.Serializable

@Serializable
data class NetworkEventPrice(
    val currency: String,
    val min: Double,
    val max: Double
)

fun NetworkEventPrice.toPriceRange(): String {
    return "$max-$min $currency"
}

fun List<NetworkEventPrice?>.toPriceRange(): String {
    return this.mapNotNull { "${it?.max}-${it?.min} ${it?.currency}" }.joinToString(separator = ",")
}
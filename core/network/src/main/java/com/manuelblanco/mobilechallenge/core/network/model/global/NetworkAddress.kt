package com.manuelblanco.mobilechallenge.core.network.model.global

import kotlinx.serialization.Serializable

@Serializable
data class NetworkAddress(
    val line1: String,
    val line2: String? = "",
    val line3: String? = ""
)

fun NetworkAddress.toFinalAddress(): String {
    return "$line1 $line2 $line3"
}

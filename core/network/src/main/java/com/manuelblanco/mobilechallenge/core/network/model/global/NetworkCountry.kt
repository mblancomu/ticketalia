package com.manuelblanco.mobilechallenge.core.network.model.global

import kotlinx.serialization.Serializable

@Serializable
data class NetworkCountry(
    val name: String,
    val countryCode: String
)

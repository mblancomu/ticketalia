package com.manuelblanco.mobilechallenge.core.network.model.event

import kotlinx.serialization.Serializable

@Serializable
data class NetworkEventStatus(
    val code: String
)
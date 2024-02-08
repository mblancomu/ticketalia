package com.manuelblanco.mobilechallenge.core.network.model.event

import kotlinx.serialization.Serializable

@Serializable
data class NetworkEventGenre(
    val id: String,
    val name: String
)

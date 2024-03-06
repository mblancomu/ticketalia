package com.manuelblanco.mobilechallenge.core.domain.model

data class Location(
    val latitude: Double,
    val longitude: Double
)

fun Location.toGoogleUri(): String =
    if (latitude != 0.0 && longitude != 0.0) "geo:$latitude,$longitude" else ""

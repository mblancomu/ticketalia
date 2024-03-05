package com.manuelblanco.mobilechallenge.core.domain.model

data class Venue(
    val id: String,
    val name: String,
    val description: String,
    val imageUrl: String,
    val location: Location,
    val country: String,
    val city: String,
    val address: String,
    val distance: String,
    val url: String
)

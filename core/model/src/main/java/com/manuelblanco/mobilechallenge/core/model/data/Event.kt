package com.manuelblanco.mobilechallenge.core.model.data

data class Event(
    val id: String,
    val name: String,
    val description: String,
    val url: String,
    val location: Location,
    val imageUrl: String,
    val dateTime: String,
    val place: String,
    val city: String,
    val country: String,
    val segment: String,
    val genres: String,
    val prices: String
)


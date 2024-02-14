package com.manuelblanco.mobilechallenge.core.testing.data

import com.manuelblanco.mobilechallenge.core.common.result.Result
import com.manuelblanco.mobilechallenge.core.model.data.Event
import com.manuelblanco.mobilechallenge.core.model.data.Location
import com.manuelblanco.mobilechallenge.core.model.data.Page

/**
 * Created by Manuel Blanco Murillo on 13/2/24.
 */

val eventDetail = Result.Success(data = Event(
    id = "1",
    name = "Alanis Stream",
    description = "",
    url = "https://www.google.com/",
    city = "Madrid",
    country = "Spain",
    location = Location(latitude = 30.1, longitude = 4.0),
    dateTime = "12/2/2023 20:30",
    genres = "pop",
    segment = "music",
    imageUrl = "",
    place = "Stadium",
    prices = "30 EUR"
)
)
var eventPageFromRemote = Result.Success(data = Page(totalElements = 20, totalPages = 3, currentPage = 1))
val eventsFromCacheList = listOf<Event>(
    Event(
        id = "1",
        name = "Alanis Stream",
        description = "",
        url = "https://www.google.com/",
        city = "Madrid",
        country = "Spain",
        location = Location(latitude = 30.1, longitude = 4.0),
        dateTime = "12/2/2023 20:30",
        genres = "pop",
        segment = "music",
        imageUrl = "",
        place = "Stadium",
        prices = "30 EUR"
    ),
    Event(
        id = "2",
        name = "Alanis Stream",
        description = "",
        url = "https://www.google.com/",
        city = "Madrid",
        country = "Spain",
        location = Location(latitude = 30.1, longitude = 4.0),
        dateTime = "12/2/2023 20:30",
        genres = "pop",
        segment = "music",
        imageUrl = "",
        place = "Stadium",
        prices = "30 EUR"
    ),
    Event(
        id = "3",
        name = "Metalica",
        description = "",
        url = "https://www.google.com/",
        city = "Madrid",
        country = "Spain",
        location = Location(latitude = 30.1, longitude = 4.0),
        dateTime = "12/2/2023 20:30",
        genres = "pop",
        segment = "music",
        imageUrl = "",
        place = "Stadium",
        prices = "30 EUR"
    ),
    Event(
        id = "4",
        name = "Real Madrid - Betis",
        description = "",
        url = "https://www.google.com/",
        city = "Madrid",
        country = "Spain",
        location = Location(latitude = 30.1, longitude = 4.0),
        dateTime = "12/2/2023 20:30",
        genres = "pop",
        segment = "music",
        imageUrl = "",
        place = "Stadium",
        prices = "30 EUR"
    )
)
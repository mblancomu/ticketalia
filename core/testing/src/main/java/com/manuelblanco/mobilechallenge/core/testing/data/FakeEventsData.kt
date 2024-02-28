package com.manuelblanco.mobilechallenge.core.testing.data

import com.manuelblanco.mobilechallenge.core.common.result.Result
import com.manuelblanco.mobilechallenge.core.model.data.Event
import com.manuelblanco.mobilechallenge.core.model.data.Location

/**
 * Created by Manuel Blanco Murillo on 13/2/24.
 */

val eventDetail = Result.Success(
    data = Event(
        id = "1",
        name = "Alanis Stream",
        description = "",
        url = "https://www.google.com/",
        city = "Madrid",
        country = "Spain",
        location = Location(latitude = 30.1, longitude = 4.0),
        dateTime = "2024-02-14T17:00:00Z",
        genres = "Pop",
        segment = "Music",
        imageUrl = "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/16/8b/24/a1/vista-del-teatro-la-latina.jpg?w=1200&h=1200&s=1",
        place = "Stadium",
        price = 30.0,
        currency = "EUR"
    )
)

val eventDetailWithoutLocation = Result.Success(
    data = Event(
        id = "1",
        name = "Alanis Stream",
        description = "",
        url = "https://www.google.com/",
        city = "Madrid",
        country = "Spain",
        location = Location(latitude = 0.0, longitude = 0.0),
        dateTime = "2024-02-14T17:00:00Z",
        genres = "Pop",
        segment = "Music",
        imageUrl = "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/16/8b/24/a1/vista-del-teatro-la-latina.jpg?w=1200&h=1200&s=1",
        place = "Stadium",
        price = 30.0,
        currency = "EUR"
    )
)

val eventDetailWithoutUrl = Result.Success(
    data = Event(
        id = "1",
        name = "Alanis Stream",
        description = "",
        url = "",
        city = "Madrid",
        country = "Spain",
        location = Location(latitude = 0.0, longitude = 0.0),
        dateTime = "2024-02-14T17:00:00Z",
        genres = "Pop",
        segment = "Music",
        imageUrl = "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/16/8b/24/a1/vista-del-teatro-la-latina.jpg?w=1200&h=1200&s=1",
        place = "Stadium",
        price = 30.0,
        currency = "EUR"
    )
)

val eventsFromCacheList = listOf<Event>(
    Event(
        id = "1",
        name = "Camela",
        description = "",
        url = "https://www.google.com/",
        city = "Madrid",
        country = "Spain",
        location = Location(latitude = 30.1, longitude = 4.0),
        dateTime = "2024-02-14T17:00:00Z",
        genres = "Pop",
        segment = "Music",
        imageUrl = "https://media.revistavanityfair.es/photos/6363f1eb868621dd08736d5a/16:9/w_1280,c_limit/Camela39314.jpg",
        place = "Stadium",
        price = 30.0,
        currency = "EUR"
    ),
    Event(
        id = "2",
        name = "Alanis Morissette",
        description = "",
        url = "https://www.google.com/",
        city = "Madrid",
        country = "Spain",
        location = Location(latitude = 30.1, longitude = 4.0),
        dateTime = "2024-02-14T17:00:00Z",
        genres = "Pop",
        segment = "Music",
        imageUrl = "https://cdn.elobservador.com.uy/adjuntos/181/imagenes/008/542/0008542463.jpg",
        place = "Stadium",
        price = 30.0,
        currency = "EUR"
    ),
    Event(
        id = "3",
        name = "Metallica",
        description = "",
        url = "https://www.google.com/",
        city = "Madrid",
        country = "Spain",
        location = Location(latitude = 30.1, longitude = 4.0),
        dateTime = "2024-02-14T17:00:00Z",
        genres = "pop",
        segment = "music",
        imageUrl = "https://s3.abcstatics.com/abc/www/multimedia/cultura/2023/04/13/Metallica-R-ReoHPlfXn08hieR7JXNNOLK-1200x840@abc.jpeg",
        place = "Stadium",
        price = 30.0,
        currency = "EUR"
    ),
    Event(
        id = "4",
        name = "Real Madrid - Betis",
        description = "",
        url = "https://www.google.com/",
        city = "Madrid",
        country = "Spain",
        location = Location(latitude = 30.1, longitude = 4.0),
        dateTime = "2024-02-14T17:00:00Z",
        genres = "pop",
        segment = "music",
        imageUrl = "https://media.timeout.com/images/106067549/image.jpg",
        place = "Stadium",
        price = 30.0,
        currency = "EUR"
    )
)
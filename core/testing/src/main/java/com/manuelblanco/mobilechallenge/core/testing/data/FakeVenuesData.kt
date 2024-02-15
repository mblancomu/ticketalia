package com.manuelblanco.mobilechallenge.core.testing.data

import androidx.paging.PagingData
import com.manuelblanco.mobilechallenge.core.common.result.Result
import com.manuelblanco.mobilechallenge.core.model.data.Location
import com.manuelblanco.mobilechallenge.core.model.data.Venue

/**
 * Created by Manuel Blanco Murillo on 13/2/24.
 */

val pagingVenues: PagingData<Venue> = PagingData.from(
    data = listOf(
        Venue(
            id = "1",
            name = "Teatro Romano",
            description = "",
            url = "https://www.google.com/",
            city = "Madrid",
            country = "Spain",
            location = Location(latitude = 30.1, longitude = 4.0),
            imageUrl = "",
            address = "",
            distance = ""
        ),
        Venue(
            id = "2",
            name = "Coliseum Arthore",
            description = "",
            url = "https://www.google.com/",
            city = "Madrid",
            country = "Spain",
            location = Location(latitude = 30.1, longitude = 4.0),
            imageUrl = "",
            address = "",
            distance = ""
        ),
        Venue(
            id = "3",
            name = "Polister Room",
            description = "",
            url = "https://www.google.com/",
            city = "Madrid",
            country = "Spain",
            location = Location(latitude = 30.1, longitude = 4.0),
            imageUrl = "",
            address = "",
            distance = ""
        ),
        Venue(
            id = "4",
            name = "Benito Villamarín",
            description = "",
            url = "https://www.google.com/",
            city = "Madrid",
            country = "Spain",
            location = Location(latitude = 30.1, longitude = 4.0),
            imageUrl = "",
            address = "",
            distance = ""
        )
    )

)
val venueDetail = Result.Success(
    data = Venue(
        id = "1",
        name = "Stadium Olympic",
        description = "",
        url = "https://www.google.com/",
        city = "Madrid",
        country = "Spain",
        location = Location(latitude = 30.1, longitude = 4.0),
        imageUrl = "",
        address = "",
        distance = ""
    )
)

val firstVenue = Venue(
    id = "1",
    name = "Teatro Romano",
    description = "",
    url = "https://www.google.com/",
    city = "Madrid",
    country = "Spain",
    location = Location(latitude = 30.1, longitude = 4.0),
    imageUrl = "",
    address = "",
    distance = ""
)

val lastVenue = Venue(
    id = "4",
    name = "Benito Villamarín",
    description = "",
    url = "https://www.google.com/",
    city = "Madrid",
    country = "Spain",
    location = Location(latitude = 30.1, longitude = 4.0),
    imageUrl = "",
    address = "",
    distance = ""
)

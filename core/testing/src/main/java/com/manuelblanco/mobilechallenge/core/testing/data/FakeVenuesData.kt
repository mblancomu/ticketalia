package com.manuelblanco.mobilechallenge.core.testing.data

import androidx.paging.PagingData
import com.manuelblanco.mobilechallenge.core.common.result.Result
import com.manuelblanco.mobilechallenge.core.model.data.Location
import com.manuelblanco.mobilechallenge.core.model.data.Venue

/**
 * Created by Manuel Blanco Murillo on 13/2/24.
 */

val pagingVenuesEmpty = PagingData.empty<Venue>()

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
            imageUrl = "https://www.canalextremadura.es/sites/default/files/Media/Images/2021-02/A_TEATRO_ROMANO_MERIDA_01.jpg",
            address = "",
            distance = ""
        ),
        Venue(
            id = "2",
            name = "Coliseum Alfonso Pérez",
            description = "",
            url = "https://www.google.com/",
            city = "Madrid",
            country = "Spain",
            location = Location(latitude = 30.1, longitude = 4.0),
            imageUrl = "https://www.lavanguardia.com/files/image_948_465/uploads/2023/10/04/651d103955ac6.jpeg",
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
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/e/e8/Tigers_opening_day2_2007.jpg",
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
            imageUrl = "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/16/8b/24/a1/vista-del-teatro-la-latina.jpg?w=1200&h=1200&s=1",
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
        imageUrl = "https://www.canalextremadura.es/sites/default/files/Media/Images/2021-02/A_TEATRO_ROMANO_MERIDA_01.jpg",
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
    imageUrl = "https://www.canalextremadura.es/sites/default/files/Media/Images/2021-02/A_TEATRO_ROMANO_MERIDA_01.jpg",
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
    imageUrl = "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/16/8b/24/a1/vista-del-teatro-la-latina.jpg?w=1200&h=1200&s=1",
    address = "",
    distance = ""
)

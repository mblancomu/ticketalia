package com.manuelblanco.mobilechallenge.core.domain.model

/**
 * Created by Manuel Blanco Murillo on 27/2/24.
 */

data class EventsFilter(
    val sortType: SortType = SortType.NAME,
    val city: String
)

enum class SortType {
    NAME,
    DATE,
    PRICE
}

enum class Cities(val city: String) {
    ALL("All"),
    MADRID("Madrid"),
    BARCELONA("Barcelona"),
    VALENCIA("Valencia"),
    SEVILLA("Sevilla"),
    IBIZA("Ibiza"),
    CADIZ("Cadiz")
}

fun List<Event>.sortAndFilterEvents(filters: EventsFilter): List<Event> {
    val filterEvents = if (filters.city != Cities.ALL.city) {
        this.filter { it.city == filters.city }
    } else this

    val finalFilter = if (filters.sortType == SortType.PRICE) {
        filterEvents.filter { it.price != 0.0 }
    } else filterEvents

    return finalFilter.sortedWith(
        compareBy {
            when (filters.sortType) {
                SortType.NAME -> it.name
                SortType.PRICE -> it.price
                SortType.DATE -> it.dateTime
            }
        }
    )
}
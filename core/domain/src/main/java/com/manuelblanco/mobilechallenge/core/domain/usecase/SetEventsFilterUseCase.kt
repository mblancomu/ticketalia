package com.manuelblanco.mobilechallenge.core.domain.usecase

import com.manuelblanco.mobilechallenge.core.domain.model.EventsFilter

/**
 * Created by Manuel Blanco Murillo on 29/2/24.
 */
interface SetEventsFilterUseCase {
    suspend operator fun invoke(filters: EventsFilter)
}
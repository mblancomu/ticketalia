package com.manuelblanco.mobilechallenge.core.domain

import com.manuelblanco.mobilechallenge.core.common.result.Result
import com.manuelblanco.mobilechallenge.core.model.data.Event
import kotlinx.coroutines.flow.Flow

interface GetEventsByRadiusUseCase {
    operator fun invoke(page: String, radius: Int, unit: String, isNetworkAvailable: Boolean): Flow<Result<List<Event>>>
}
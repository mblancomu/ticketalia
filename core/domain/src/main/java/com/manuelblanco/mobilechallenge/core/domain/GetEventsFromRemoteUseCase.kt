package com.manuelblanco.mobilechallenge.core.domain

import com.manuelblanco.mobilechallenge.core.common.result.Result
import com.manuelblanco.mobilechallenge.core.model.data.Page
import kotlinx.coroutines.flow.Flow

interface GetEventsFromRemoteUseCase {
    operator fun invoke(page: String, isRefreshing: Boolean): Flow<Result<Page>>
}
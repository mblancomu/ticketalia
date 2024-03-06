package com.manuelblanco.mobilechallenge.core.domain.usecase

interface GetEventsRemoteFirstUseCase {
    suspend operator fun invoke(page: String, keyword: String, isRefreshing: Boolean)
}
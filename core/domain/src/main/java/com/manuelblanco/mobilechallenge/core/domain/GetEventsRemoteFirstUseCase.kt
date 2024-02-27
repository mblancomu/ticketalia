package com.manuelblanco.mobilechallenge.core.domain

interface GetEventsRemoteFirstUseCase {
    suspend operator fun invoke(page: String, keyword: String, isRefreshing: Boolean)
}
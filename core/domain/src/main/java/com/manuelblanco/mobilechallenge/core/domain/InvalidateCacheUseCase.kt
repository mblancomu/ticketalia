package com.manuelblanco.mobilechallenge.core.domain

import com.manuelblanco.mobilechallenge.core.model.data.Event
import kotlinx.coroutines.flow.Flow

interface InvalidateCacheUseCase {
    suspend operator fun invoke()
}
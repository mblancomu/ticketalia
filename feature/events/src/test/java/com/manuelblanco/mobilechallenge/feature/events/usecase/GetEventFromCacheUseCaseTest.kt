package com.manuelblanco.mobilechallenge.feature.events.usecase

import com.manuelblanco.mobilechallenge.core.common.result.Result
import com.manuelblanco.mobilechallenge.core.domain.usecase.GetEventFromCacheUseCase
import com.manuelblanco.mobilechallenge.core.testing.repository.usecase.FakeEventsRepository
import com.manuelblanco.mobilechallenge.core.domain.usecase.GetEventFromCacheUseCaseImpl
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import kotlin.test.assertTrue

/**
 * Created by Manuel Blanco Murillo on 13/2/24.
 */
class GetEventFromCacheUseCaseTest {

    private lateinit var getEventFromCacheUseCase: GetEventFromCacheUseCase
    private lateinit var fakeEventsRepository: FakeEventsRepository

    @Before
    fun setUp() {
        fakeEventsRepository = FakeEventsRepository()
        getEventFromCacheUseCase = GetEventFromCacheUseCaseImpl(fakeEventsRepository)
    }

    @Test
    fun `Get single event from cache, return the event by id`(): Unit = runBlocking {
        val events = getEventFromCacheUseCase("1")
        events.collect { result ->
            when (result) {
                is Result.Error -> {}
                Result.Loading -> {}
                is Result.Success -> {
                    assertTrue(result.data.id == "1")
                }
            }
        }
    }

    @Test
    fun `Get single event from cache, return a error message when the id param in empty`(): Unit =
        runBlocking {
            val events = getEventFromCacheUseCase("")
            events.collect { result ->
                when (result) {
                    is Result.Error -> {
                        assertTrue(result.exception?.message == "Wrong id")
                    }

                    Result.Loading -> {}
                    is Result.Success -> {}
                }
            }
        }
}
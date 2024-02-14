package com.manuelblanco.mobilechallenge.feature.events.usecase

import com.manuelblanco.mobilechallenge.core.domain.GetEventsFromCacheUseCase
import com.manuelblanco.mobilechallenge.core.testing.repository.usecase.FakeEventsRepository
import com.manuelblanco.mobilechallenge.feature.events.usecases.GetEventsFromCacheUseCaseImpl
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import kotlin.test.assertTrue

/**
 * Created by Manuel Blanco Murillo on 13/2/24.
 */
class GetEventsFromCacheUseCaseTest {


    private lateinit var getEventsFromCacheUseCase: GetEventsFromCacheUseCase
    private lateinit var fakeEventsRepository: FakeEventsRepository

    @Before
    fun setUp() {
        fakeEventsRepository = FakeEventsRepository()
        getEventsFromCacheUseCase = GetEventsFromCacheUseCaseImpl(fakeEventsRepository)
    }

    @Test
    fun `Get events from cache, not empty list of events return`(): Unit = runBlocking {
        val events = getEventsFromCacheUseCase(limit = 0, offset = 4)
        events.collect { result ->
            assertTrue(result.isNotEmpty())
        }
    }

    @Test
    fun `Get events from cache, first event in the list is correct as expected`(): Unit =
        runBlocking {
            val events = getEventsFromCacheUseCase(limit = 0, offset = 4)
            events.collect { result ->
                assertTrue(result.first().id == "1")
            }
        }

    @Test
    fun `Get events from cache, last event in the list is correct as expected`(): Unit =
        runBlocking {
            val events = getEventsFromCacheUseCase(limit = 0, offset = 4)
            events.collect { result ->
                assertTrue(result.last().id == "4")
            }
        }

    @Test
    fun `Get events from cache, offset should be minor than list size, return an empty list`(): Unit =
        runBlocking {
            val events = getEventsFromCacheUseCase(limit = 0, offset = 6)
            events.collect { result ->
                assertTrue(result.isEmpty())
            }
        }
}

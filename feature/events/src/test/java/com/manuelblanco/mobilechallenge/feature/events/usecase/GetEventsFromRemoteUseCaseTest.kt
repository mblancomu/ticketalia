package com.manuelblanco.mobilechallenge.feature.events.usecase

import com.manuelblanco.mobilechallenge.core.common.result.Result
import com.manuelblanco.mobilechallenge.core.domain.GetEventsFromRemoteUseCase
import com.manuelblanco.mobilechallenge.core.testing.repository.usecase.FakeEventsRepository
import com.manuelblanco.mobilechallenge.feature.events.usecases.GetEventsFromRemoteUseCaseImpl
import junit.framework.TestCase.assertFalse
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import kotlin.test.assertTrue

/**
 * Created by Manuel Blanco Murillo on 13/2/24.
 */
class GetEventsFromRemoteUseCaseTest {

    private lateinit var getEventsFromRemoteUseCase: GetEventsFromRemoteUseCase
    private lateinit var fakeEventsRepository: FakeEventsRepository

    @Before
    fun setUp() {
        fakeEventsRepository = FakeEventsRepository()
        getEventsFromRemoteUseCase = GetEventsFromRemoteUseCaseImpl(fakeEventsRepository)
    }

    @Test
    fun `Get page from remote, correct page return`(): Unit = runBlocking {
        val page = getEventsFromRemoteUseCase(page = "1")
        page.collect { result ->
            when (result) {
                is Result.Error -> {}
                Result.Loading -> {}
                is Result.Success -> {
                    assertTrue(result.data.currentPage == 1)
                }
            }
        }
    }

    @Test
    fun `Get page from remote, wrong current page return`(): Unit = runBlocking {
        val page = getEventsFromRemoteUseCase(page = "1")
        page.collect { result ->
            when (result) {
                is Result.Error -> {}
                Result.Loading -> {}
                is Result.Success -> {
                    assertFalse(result.data.currentPage == 2)
                }
            }
        }
    }

    @Test
    fun `Get page from remote, correct total pages return`(): Unit = runBlocking {
        val page = getEventsFromRemoteUseCase(page = "1")
        page.collect { result ->
            when (result) {
                is Result.Error -> {}
                Result.Loading -> {}
                is Result.Success -> {
                    assertTrue(result.data.totalPages == 3)
                }
            }
        }
    }

    @Test
    fun `Get page from remote, wrong total pages return`(): Unit = runBlocking {
        val page = getEventsFromRemoteUseCase(page = "1")
        page.collect { result ->
            when (result) {
                is Result.Error -> {}
                Result.Loading -> {}
                is Result.Success -> {
                    assertFalse(result.data.totalPages == 5)
                }
            }
        }
    }

    @Test
    fun `Get page from remote, correct total elements return`(): Unit = runBlocking {
        val page = getEventsFromRemoteUseCase(page = "1")
        page.collect { result ->
            when (result) {
                is Result.Error -> {}
                Result.Loading -> {}
                is Result.Success -> {
                    assertTrue(result.data.totalElements == 20)
                }
            }
        }
    }

    @Test
    fun `Get page from remote, wrong total elements return`(): Unit = runBlocking {
        val page = getEventsFromRemoteUseCase(page = "1")
        page.collect { result ->
            when (result) {
                is Result.Error -> {}
                Result.Loading -> {}
                is Result.Success -> {
                    assertFalse(result.data.totalElements == 5)
                }
            }
        }
    }

    @Test
    fun `Get page from remote, wrong parameter page calling method`(): Unit = runBlocking {
        val page = getEventsFromRemoteUseCase(page = "")
        page.collect { result ->
            when (result) {
                is Result.Error -> {
                   assertTrue(result.exception?.message == "Parameter page empty")
                }
                Result.Loading -> {}
                is Result.Success -> {
                }
            }
        }
    }

    @Test
    fun `Get page from remote, return a page number different than that of the parameter`(): Unit = runBlocking {
        val page = getEventsFromRemoteUseCase(page = "2")
        page.collect { result ->
            when (result) {
                is Result.Error -> {
                    assertTrue(result.exception?.message == "Parameter page do not match")
                }
                Result.Loading -> {}
                is Result.Success -> {
                    assertTrue(result.data.currentPage == 1)
                }
            }
        }
    }
}
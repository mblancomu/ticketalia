package com.manuelblanco.venues.usecase

import com.manuelblanco.mobilechallenge.core.common.result.Result
import com.manuelblanco.mobilechallenge.core.domain.GetVenueUseCase
import com.manuelblanco.mobilechallenge.core.testing.repository.usecase.FakeVenuesRepository
import com.manuelblanco.mobilechallenge.feature.venues.usecases.GetVenueUseCaseImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertTrue

/**
 * Created by Manuel Blanco Murillo on 13/2/24.
 */
class GetVenueUseCaseTest {

    private lateinit var getVenueUseCase: GetVenueUseCase
    private lateinit var fakeVenuesRepository: FakeVenuesRepository

    @Before
    fun setUp() {
        fakeVenuesRepository = FakeVenuesRepository()
        getVenueUseCase = GetVenueUseCaseImpl(fakeVenuesRepository)
    }

    @Test
    fun `Get single venue from cache, return the venue by id`(): Unit = runBlocking {
        val venue = getVenueUseCase("1")
        venue.collect { result ->
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
    fun `Get single venue from cache, return a error message when the id param in empty`(): Unit =
        runBlocking {
            val venue = getVenueUseCase("")
            venue.collect { result ->
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
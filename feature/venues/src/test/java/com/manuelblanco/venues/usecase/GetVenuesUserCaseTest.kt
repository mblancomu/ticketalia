package com.manuelblanco.venues.usecase

import androidx.paging.map
import com.manuelblanco.mobilechallenge.core.common.result.Result
import com.manuelblanco.mobilechallenge.core.common.result.asResult
import com.manuelblanco.mobilechallenge.core.domain.usecase.GetVenuesUseCase
import com.manuelblanco.mobilechallenge.core.testing.data.firstVenue
import com.manuelblanco.mobilechallenge.core.testing.data.lastVenue
import com.manuelblanco.mobilechallenge.core.testing.repository.usecase.FakeVenuesRepository
import com.manuelblanco.mobilechallenge.core.domain.usecase.GetVenuesUseCaseImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

/**
 * Created by Manuel Blanco Murillo on 13/2/24.
 */
class GetVenuesUserCaseTest {

    @ExperimentalCoroutinesApi
    private val testDispatcher = StandardTestDispatcher()

    private lateinit var getVenuesUseCase: GetVenuesUseCase
    private lateinit var fakeVenuesRepository: FakeVenuesRepository

    @Before
    fun setUp() {
        fakeVenuesRepository = FakeVenuesRepository()
        getVenuesUseCase = GetVenuesUseCaseImpl(fakeVenuesRepository)
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `Get venues with paging, not empty list of venues return`(): Unit = runBlocking {
        getVenuesUseCase().asResult().collect { result ->
            when (result) {
                is Result.Error -> {}
                Result.Loading -> {}
                is Result.Success -> {
                    result.data.map {
                        assertNotNull(it)
                    }
                }
            }
        }
    }

    @Test
    fun `Get venues with paging, first venue in the list is correct as expected`(): Unit =
        runBlocking {
            getVenuesUseCase().asResult().collect { result ->
                when (result) {
                    is Result.Error -> {}
                    Result.Loading -> {}
                    is Result.Success -> {
                        result.data.map {
                            assertTrue(it.id == "1")
                            assertEquals(it, firstVenue)
                        }
                    }
                }
            }
        }

    @Test
    fun `Get venues with paging, last venue in the list is correct as expected`(): Unit =
        runBlocking {
            getVenuesUseCase().asResult().collect { result ->
                when (result) {
                    is Result.Error -> {}
                    Result.Loading -> {}
                    is Result.Success -> {
                        result.data.map {
                            assertTrue(it.id == "4")
                            assertEquals(it, lastVenue)
                        }
                    }
                }
            }
        }
}
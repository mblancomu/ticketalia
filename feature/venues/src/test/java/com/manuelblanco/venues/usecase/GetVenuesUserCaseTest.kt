package com.manuelblanco.venues.usecase

import com.manuelblanco.mobilechallenge.core.common.result.Result
import com.manuelblanco.mobilechallenge.core.common.result.asResult
import com.manuelblanco.mobilechallenge.core.testing.utils.collectDataForTest
import com.manuelblanco.mobilechallenge.core.domain.GetVenuesUseCase
import com.manuelblanco.mobilechallenge.core.testing.repository.usecase.FakeVenuesRepository
import com.manuelblanco.mobilechallenge.feature.venues.usecases.GetVenuesUseCaseImpl
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import kotlin.test.assertTrue

/**
 * Created by Manuel Blanco Murillo on 13/2/24.
 */
class GetVenuesUserCaseTest {

    private lateinit var getVenuesUseCase: GetVenuesUseCase
    private lateinit var fakeVenuesRepository: FakeVenuesRepository

    @Before
    fun setUp() {
        fakeVenuesRepository = FakeVenuesRepository()
        getVenuesUseCase = GetVenuesUseCaseImpl(fakeVenuesRepository)
    }

    @Test
    fun `Get venues with paging, not empty list of venues return`(): Unit = runBlocking {
        getVenuesUseCase().asResult().collect { result ->
            when (result) {
                is Result.Error -> {}
                Result.Loading -> {}
                is Result.Success -> {
                    val venues = result.data.collectDataForTest()
                    assertTrue(venues.isNotEmpty())
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
                        val venues = result.data.collectDataForTest()
                        assertTrue(venues.first().id == "1")
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
                        val venues = result.data.collectDataForTest()
                        assertTrue(venues.last().id == "4")
                    }
                }
            }
        }
}
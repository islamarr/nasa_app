package com.adyen.android.assignment.features.main_screen.data.repositories

import com.adyen.android.assignment.TestAppCoroutineDispatchers
import com.adyen.android.assignment.TestCoroutineDispatcherRule
import com.adyen.android.assignment.common.data.NetworkResponse
import com.adyen.android.assignment.features.main_screen.data.remote.api.PlanetaryService
import com.adyen.android.assignment.features.main_screen.domain.entities.AstronomyPicture
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
internal class PlanetaryRepositoryImplTest {

    @get:Rule
    val coroutineRule = TestCoroutineDispatcherRule()

    private lateinit var repository: PlanetaryRepositoryImpl

    @Mock
    private lateinit var apiService: PlanetaryService

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repository = PlanetaryRepositoryImpl(
            TestAppCoroutineDispatchers(coroutineRule.testDispatcher),
            apiService
        )
    }

    @Test
    fun `test get pictures in success statue`() = runTest {
        val response = listOf(AstronomyPicture())
        whenever(apiService.getPictures()).thenReturn(response)

        val actual = repository.getPictures()
        val expected = NetworkResponse.Success(response)

        assertEquals(expected, actual)
    }

    @Test
    fun `test get pictures in failure statue`() = runTest {
        whenever(apiService.getPictures()).thenThrow(RuntimeException())

        val actual = repository.getPictures()
        val expected = NetworkResponse.Failure<AstronomyPicture>()

        assertEquals(expected, actual)
    }

}
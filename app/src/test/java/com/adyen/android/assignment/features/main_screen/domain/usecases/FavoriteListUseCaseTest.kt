package com.adyen.android.assignment.features.main_screen.domain.usecases

import com.adyen.android.assignment.common.data.NetworkResponse
import com.adyen.android.assignment.features.details.domain.repositories.DetailsRepository
import com.adyen.android.assignment.features.main_screen.domain.entities.AstronomyPicture
import com.adyen.android.assignment.features.main_screen.domain.repositories.PlanetaryRepository
import com.adyen.android.assignment.features.main_screen.presentation.viewmodel.MainScreenResults
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
internal class FavoriteListUseCaseTest {
    @Mock
    private lateinit var repository: DetailsRepository

    private lateinit var useCase: FavoriteListUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = FavoriteListUseCase(repository)
    }

    @Test
    fun `test execute useCase with filled list Result`() = runTest {
        whenever(repository.getFavoriteList()).thenReturn(flow { listOf(AstronomyPicture()) })

        useCase.execute().collectLatest { expected ->
            assertEquals(listOf(AstronomyPicture()), expected)
        }

    }

    @Test
    fun `test execute useCase with Empty list Result`() = runTest {
        whenever(repository.getFavoriteList()).thenReturn(flow { listOf<AstronomyPicture>() })

        useCase.execute().collectLatest { expected ->
            assertEquals(listOf<AstronomyPicture>(), expected)
        }

    }
}
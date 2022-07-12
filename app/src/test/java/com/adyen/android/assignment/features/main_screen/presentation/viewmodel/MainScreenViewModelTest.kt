package com.adyen.android.assignment.features.main_screen.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.adyen.android.assignment.TestCoroutineDispatcherRule
import com.adyen.android.assignment.features.main_screen.domain.usecases.FavoriteListUseCase
import com.adyen.android.assignment.features.main_screen.domain.usecases.PlanetaryUseCase
import com.adyen.android.assignment.features.main_screen.domain.usecases.SortListUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
internal class MainScreenViewModelTest {
    private lateinit var viewModel: MainScreenViewModel

    @Mock
    private lateinit var planetaryUseCase: PlanetaryUseCase

    @Mock
    private lateinit var sortListUseCase: SortListUseCase

    @Mock
    private lateinit var favoriteListUseCase: FavoriteListUseCase

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = TestCoroutineDispatcherRule()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel =
            MainScreenViewModel(planetaryUseCase, sortListUseCase, favoriteListUseCase)
    }

    @Test
    fun `when handle LoadAstronomyPicture action return Loading as a first result`() =
        runTest {
            val action = MainScreenActions.LoadAstronomyPicture
            val result = MainScreenResults.Loading
            val actual = viewModel.handle(action).first()

            assertEquals(result, actual)
        }

    @Test
    fun `when handle LoadAstronomyPicture action return EmptyList as a second result`() =
        runTest {
            val action = MainScreenActions.LoadAstronomyPicture
            val result = MainScreenResults.EmptyList

            whenever(planetaryUseCase.execute()).thenReturn(MainScreenResults.EmptyList)
            val actual = viewModel.handle(action).drop(1).first()

            assertEquals(result, actual)
        }

    @Test
    fun `when handle LoadFavoriteList action return EmptyFavoriteList as a first result`() =
        runTest {
            val action = MainScreenActions.LoadFavoriteList
            whenever(favoriteListUseCase.execute()).thenReturn(flow { MainScreenResults.EmptyList })

            favoriteListUseCase.execute().collectLatest {
                val actual = viewModel.handle(action).first()
                assertEquals(it, actual)
            }
        }
}
package com.adyen.android.assignment.features.details.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.adyen.android.assignment.TestCoroutineDispatcherRule
import com.adyen.android.assignment.features.details.domain.usecases.GetFavoriteUseCase
import com.adyen.android.assignment.features.details.domain.usecases.SetFavoriteUseCase
import com.adyen.android.assignment.features.details.presentation.view.DetailsFragmentArgs
import com.adyen.android.assignment.features.main_screen.domain.entities.AstronomyPicture
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
internal class DetailsViewModelTest {

    private lateinit var viewModel: DetailsViewModel

    private lateinit var savedStateHandle: SavedStateHandle

    @Mock
    private lateinit var getFavoriteUseCase: GetFavoriteUseCase

    @Mock
    private lateinit var setFavoriteUseCase: SetFavoriteUseCase

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = TestCoroutineDispatcherRule()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        savedStateHandle = SavedStateHandle().apply {
            set("astronomyPicture", AstronomyPicture(title = "", date = ""))
        }
        viewModel =
            DetailsViewModel(getFavoriteUseCase, setFavoriteUseCase, savedStateHandle)
    }

    @Test
    fun `when handle CheckAstronomyPictureFavorite action return Saved State as a first result`() =
        runTest {
            val args = DetailsFragmentArgs.fromSavedStateHandle(savedStateHandle)
            val title = args.astronomyPicture.title ?: ""
            val date = args.astronomyPicture.date ?: ""
            val action = DetailsActions.CheckAstronomyPictureFavorite(title, date)
            val result = DetailsResults.SavedState(true)

            whenever(getFavoriteUseCase.execute(title, date)).thenReturn(result)
            val actual = viewModel.handle(action).first()

            assertEquals(result, actual)
        }

    @Test
    fun `when dispatch Check Astronomy Picture Favorite return Saved State as a first state`() =
        runTest {
            val args = DetailsFragmentArgs.fromSavedStateHandle(savedStateHandle)
            val title = args.astronomyPicture.title ?: ""
            val date = args.astronomyPicture.date ?: ""
            val action = DetailsActions.CheckAstronomyPictureFavorite(title, date)
            val result = DetailsResults.SavedState(true)

            val job = launch {
                viewModel.state.collect()
            }
            whenever(getFavoriteUseCase.execute(title, date)).thenReturn(result)

            viewModel.dispatch(action)

            val actual = viewModel.state.first()
            val expected = DetailsStates.SavedState(true)

            assertEquals(expected, actual)

            job.cancel()
        }
}
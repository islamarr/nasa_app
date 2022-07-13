package com.adyen.android.assignment.features.details.domain.usecases

import com.adyen.android.assignment.features.details.domain.repositories.DetailsRepository
import com.adyen.android.assignment.features.details.presentation.viewmodel.DetailsResults
import com.adyen.android.assignment.features.main_screen.domain.entities.AstronomyPicture
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
internal class GetFavoriteUseCaseTest {

    @Mock
    private lateinit var repository: DetailsRepository

    private lateinit var useCase: GetFavoriteUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = GetFavoriteUseCase(repository)
    }

    @Test
    fun `when the Favorite Item exists return true state`() = runTest {
        whenever(repository.getOneFavoriteItem("", "")).thenReturn(AstronomyPicture())

        val actual = useCase.execute("", "")
        val expected = DetailsResults.SavedState(true)

        assertEquals(actual, expected)
    }

    @Test
    fun `when the Favorite Item not exists return false state`() = runTest {
        whenever(repository.getOneFavoriteItem("", "")).thenReturn(null)

        val actual = useCase.execute("", "")
        val expected = DetailsResults.SavedState(false)

        assertEquals(actual, expected)
    }
}
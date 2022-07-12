package com.adyen.android.assignment.features.main_screen.domain.usecases

import com.adyen.android.assignment.common.data.NetworkResponse
import com.adyen.android.assignment.features.main_screen.domain.entities.AstronomyPicture
import com.adyen.android.assignment.features.main_screen.domain.repositories.PlanetaryRepository
import com.adyen.android.assignment.features.main_screen.presentation.viewmodel.MainScreenResults
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
internal class PlanetaryUseCaseTest {

    @Mock
    private lateinit var repository: PlanetaryRepository

    private lateinit var useCase: PlanetaryUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = PlanetaryUseCase(repository)
    }

    @Test
    fun `when execute useCase with success response return AstronomyListLoaded Result`() = runTest {
        val list = listOf(AstronomyPicture())
        whenever(repository.getPictures()).thenReturn(
            NetworkResponse.Success(
                list
            )
        )

        val actual = useCase.execute()
        val expected = MainScreenResults.AstronomyListLoaded(list)

        assertEquals(actual, expected)
    }

    @Test
    fun `when execute useCase with failure response return EmptyList Result`() = runTest {
        val response = NetworkResponse.Failure<List<AstronomyPicture>>("reason")
        whenever(repository.getPictures()).thenReturn(response)

        val actual = useCase.execute()
        val expected = MainScreenResults.ErrorMessage(response.reason)

        assertEquals(actual, expected)
    }

    @Test
    fun `test failure Response with null reason`() = runTest {
        val response = NetworkResponse.Failure<List<AstronomyPicture>>()
        whenever(repository.getPictures()).thenReturn(response)

        val actual = useCase.execute()
        val expected = MainScreenResults.ErrorMessage()

        assertEquals(actual, expected)
    }

    @Test
    fun `when get null response return EmptyList Result`() = runTest {
        whenever(repository.getPictures()).thenReturn(NetworkResponse.Success(null))

        val actual = useCase.execute()
        val expected = MainScreenResults.EmptyList

        assertEquals(actual, expected)
    }
/*


    @Test
    fun `when get null response return ShowErrorMessage`() = runTest {
        val albumParams = AlbumParams(artistName = "artist", albumName = "album")
        whenever(repository.getAlbumDetails(albumParams)).thenReturn(DataResponse.Success(null))

        val actual = useCase.execute(albumParams)
        val expected = AlbumDetailsStates.ShowErrorMessage()

        assertEquals(actual, expected)
    }*/

}
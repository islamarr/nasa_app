package com.adyen.android.assignment.features.details.data.repositories

import com.adyen.android.assignment.TestAppCoroutineDispatchers
import com.adyen.android.assignment.TestCoroutineDispatcherRule
import com.adyen.android.assignment.common.data.NetworkResponse
import com.adyen.android.assignment.features.details.data.db.FavoriteDao
import com.adyen.android.assignment.features.main_screen.domain.entities.AstronomyPicture
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
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
internal class DetailsRepositoryImplTest {
    @get:Rule
    val coroutineRule = TestCoroutineDispatcherRule()

    private lateinit var repository: DetailsRepositoryImpl

    @Mock
    private lateinit var favoriteDao: FavoriteDao

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repository = DetailsRepositoryImpl(
            favoriteDao,
            TestAppCoroutineDispatchers(coroutineRule.testDispatcher),
        )
    }

    @Test
    fun `test get Favorite List`() = runTest {
        val response = listOf(AstronomyPicture())
        whenever(favoriteDao.getFavoriteList()).thenReturn(flow { listOf(AstronomyPicture()) })

        repository.getFavoriteList().collectLatest {
            val expected = NetworkResponse.Success(response)
            assertEquals(expected, it)
        }

    }

    @Test
    fun `test get One Favorite Item in success statue`() = runTest {
        val result = AstronomyPicture()
        whenever(favoriteDao.getOneFavoriteItem("", "")).thenReturn(result)

        val actual = repository.getOneFavoriteItem("", "")
        assertEquals(result, actual)
    }

}
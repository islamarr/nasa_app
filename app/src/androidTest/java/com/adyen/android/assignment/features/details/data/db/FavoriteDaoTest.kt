package com.adyen.android.assignment.features.details.data.db

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.adyen.android.assignment.features.main_screen.domain.entities.AstronomyPicture
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers
import org.junit.*
import org.junit.runner.RunWith
import java.io.IOException

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
internal class FavoriteDaoTest : TestCase() {

    private lateinit var favoriteDao: FavoriteDao
    private lateinit var db: FavoriteDatabase

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = TestCoroutineDispatcherRule()

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, FavoriteDatabase::class.java).build()
        favoriteDao = db.getFavoriteDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertNewFavorite_ReadInList() = runTest {
        val item = AstronomyPicture(
            id = 1,
            title = "title_1",
            date = "2001-01-10",
            explanation = "explanation_1"
        )
        favoriteDao.addToFavoriteList(item)

        val job = launch(coroutineRule.testDispatcher) {
            favoriteDao.getFavoriteList().collect { list ->
                assertThat(list[0], CoreMatchers.equalTo(item))
            }
        }

        job.cancel()

    }

    @Test
    @Throws(Exception::class)
    fun insertNewFavorite_GetOneFavorite() = runTest {
        val item = AstronomyPicture(
            id = 1,
            title = "title_1",
            date = "2001-01-10",
            explanation = "explanation_1"
        )
        favoriteDao.addToFavoriteList(item)
        val favorite =
            favoriteDao.getOneFavoriteAlbum(item.title!!, item.date!!)
        assertThat(favorite, CoreMatchers.equalTo(item))
    }

    @Test
    @Throws(Exception::class)
    fun insertAndDeleteFavorite_ReturnEmptyList() = runTest {
        val item = AstronomyPicture(
            id = 1,
            title = "title_1",
            date = "2001-01-10",
            explanation = "explanation_1"
        )
        favoriteDao.addToFavoriteList(item)
        favoriteDao.removeFromFavoriteList(item.title!!, item.date!!)
        val job = launch(coroutineRule.testDispatcher) {
            favoriteDao.getFavoriteList().collect { list ->
                assertThat(list.size, CoreMatchers.equalTo(0))
            }
        }

        job.cancel()
    }

}
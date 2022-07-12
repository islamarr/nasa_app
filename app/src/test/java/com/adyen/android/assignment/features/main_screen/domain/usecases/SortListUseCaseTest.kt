package com.adyen.android.assignment.features.main_screen.domain.usecases

import com.adyen.android.assignment.common.DATE_SORT
import com.adyen.android.assignment.common.TITLE_SORT
import com.adyen.android.assignment.common.UNSORTED
import com.adyen.android.assignment.features.main_screen.domain.entities.AstronomyPicture
import com.adyen.android.assignment.features.main_screen.presentation.viewmodel.MainScreenResults
import org.junit.Assert
import org.junit.Test

internal class SortListUseCaseTest {

    private val sortListUseCase = SortListUseCase()
    private val astronomyPictureList = mutableListOf(
        AstronomyPicture(
            id = 2,
            title = "title_2",
            date = "2002-02-10",
            explanation = "explanation_2"
        ), AstronomyPicture(
            id = 3,
            title = "title_3",
            date = "2003-03-10",
            explanation = "explanation_3"
        ), AstronomyPicture(
            id = 1,
            title = "title_1",
            date = "2001-01-10",
            explanation = "explanation_1"
        )
    )

    @Test
    fun `when load unsorted list return the default list`() {
        val actual = sortListUseCase.invoke(UNSORTED, astronomyPictureList)
        val expected = MainScreenResults.AstronomyListLoaded(astronomyPictureList)

        Assert.assertEquals(actual, expected)
    }

    @Test
    fun `when load list sorted by TITLE return the correct sorted list`() {
        val actual = sortListUseCase.invoke(TITLE_SORT, astronomyPictureList)
        val sortedList = mutableListOf(
            AstronomyPicture(
                id = 1,
                title = "title_1",
                date = "2001-01-10",
                explanation = "explanation_1"
            ), AstronomyPicture(
                id = 2,
                title = "title_2",
                date = "2002-02-10",
                explanation = "explanation_2"
            ), AstronomyPicture(
                id = 3,
                title = "title_3",
                date = "2003-03-10",
                explanation = "explanation_3"
            )
        )
        val expected = MainScreenResults.FilteredList(sortedList)

        Assert.assertEquals(actual, expected)
    }

    @Test
    fun `when load list sorted by DATE return the correct sorted list`() {
        val actual = sortListUseCase.invoke(DATE_SORT, astronomyPictureList)
        val sortedList = mutableListOf(
            AstronomyPicture(
                id = 3,
                title = "title_3",
                date = "2003-03-10",
                explanation = "explanation_3"
            ), AstronomyPicture(
                id = 2,
                title = "title_2",
                date = "2002-02-10",
                explanation = "explanation_2"
            ), AstronomyPicture(
                id = 1,
                title = "title_1",
                date = "2001-01-10",
                explanation = "explanation_1"
            )
        )
        val expected = MainScreenResults.FilteredList(sortedList)

        Assert.assertEquals(actual, expected)
    }
}
package com.adyen.android.assignment.features.details.presentation.viewmodel

import com.adyen.android.assignment.common.ui.BaseViewModel
import com.adyen.android.assignment.features.details.domain.usecases.GetFavoriteUseCase
import com.adyen.android.assignment.features.details.domain.usecases.SetFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getFavoriteUseCase: GetFavoriteUseCase,
    private val setFavoriteUseCase: SetFavoriteUseCase
) :
    BaseViewModel<DetailsStates, DetailsActions, DetailsEvents, DetailsResults>(DetailsStates.InitialState) {

    override fun handle(actions: DetailsActions): Flow<DetailsResults> = flow {
        when (actions) {
            is DetailsActions.CheckAstronomyPictureFavorite -> emit(
                getFavoriteUseCase.execute(
                    actions.title, actions.date
                )
            )
            is DetailsActions.SetFavoriteAction -> setFavoriteUseCase.execute(
                actions.isAdd,
                actions.astronomyPicture
            )
        }
    }

    override fun reduce(result: DetailsResults): DetailsStates {
        return when (result) {
            is DetailsResults.SavedState -> DetailsStates.SavedState(result.isSaved)
        }
    }
}

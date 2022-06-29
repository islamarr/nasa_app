package com.adyen.android.assignment.features.general_errors

import com.adyen.android.assignment.common.Action
import com.adyen.android.assignment.common.Results
import com.adyen.android.assignment.common.ViewEvents
import com.adyen.android.assignment.common.ViewState
import com.adyen.android.assignment.common.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class GeneralErrorViewModel @Inject constructor() :
    BaseViewModel<ViewState, Action, ViewEvents, Results>(GeneralErrorViewState.InitialState) {

    override fun handle(actions: Action): Flow<Results> = flow {}
    override fun reduce(result: Results): ViewState {
        return GeneralErrorViewState.InitialState
    }
}

sealed class GeneralErrorViewState : ViewState {
    object InitialState : GeneralErrorViewState()
}
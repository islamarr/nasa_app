package com.adyen.android.assignment.features.main_screen.presentation.viewmodel

import com.adyen.android.assignment.common.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor() :
    BaseViewModel<MainScreenStates, MainScreenActions>(MainScreenStates.InitialState) {

    override fun handle(actions: MainScreenActions): Flow<MainScreenStates> = flow {

    }

}
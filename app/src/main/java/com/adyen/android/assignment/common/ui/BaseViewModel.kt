package com.adyen.android.assignment.common.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adyen.android.assignment.common.Action
import com.adyen.android.assignment.common.ViewState
import kotlinx.coroutines.flow.*

abstract class BaseViewModel<STATES : ViewState, ACTIONS : Action>(initialState: STATES) :
    ViewModel() {
    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<STATES>
        get() = _state.asStateFlow()

    fun dispatch(actions: ACTIONS) {
        handle(actions).onEach { state ->
            onViewState(state)
        }.launchIn(viewModelScope)
    }

    private suspend fun onViewState(state: STATES) {
        _state.emit(state)
    }

    abstract fun handle(actions: ACTIONS): Flow<STATES>

}
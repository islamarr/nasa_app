package com.adyen.android.assignment.common.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adyen.android.assignment.common.Action
import com.adyen.android.assignment.common.Results
import com.adyen.android.assignment.common.ViewEvents
import com.adyen.android.assignment.common.ViewState
import com.adyen.android.assignment.features.main_screen.presentation.viewmodel.MainScreenEvents
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class BaseViewModel<STATES : ViewState, ACTIONS : Action, EVENTS : ViewEvents, RESULT : Results>(
    initialState: STATES
) : ViewModel() {

    val currentState: STATES
        get() = state.value

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<STATES>
        get() = _state.asStateFlow()

    private val _uiEvent: Channel<EVENTS> = Channel()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun dispatch(actions: ACTIONS) {
        handle(actions).map { result ->
            reduce(result)
        }.onEach { state ->
            onViewState(state)
        }.launchIn(viewModelScope)
    }

    private suspend fun onViewState(state: STATES) {
        _state.emit(state)
    }

    fun onViewEvent(event: EVENTS) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

    abstract fun handle(actions: ACTIONS): Flow<RESULT>

    abstract fun reduce(result: RESULT): STATES

}
package com.adyen.android.assignment.common.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adyen.android.assignment.common.Action
import com.adyen.android.assignment.common.Results
import com.adyen.android.assignment.common.ViewEvents
import com.adyen.android.assignment.common.ViewState
import com.adyen.android.assignment.features.main_screen.presentation.viewmodel.MainScreenEvents
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class BaseViewModel<STATES : ViewState, ACTIONS : Action, EVENTS : ViewEvents, RESULT : Results> : ViewModel() {

    val currentState: STATES
        get() = state.replayCache[0]

    private val _state = MutableSharedFlow<STATES>(replay = 2) //TODO Fix add to fav then rotate
    val state: SharedFlow<STATES>
        get() = _state.asSharedFlow()

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
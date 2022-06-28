package com.adyen.android.assignment.features.main_screen.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.adyen.android.assignment.common.Action
import com.adyen.android.assignment.common.ViewState
import com.adyen.android.assignment.common.ui.BaseFragment
import com.adyen.android.assignment.common.ui.BaseViewModel
import com.adyen.android.assignment.databinding.FragmentMainScreenBinding
import com.adyen.android.assignment.features.main_screen.presentation.viewmodel.MainScreenActions
import com.adyen.android.assignment.features.main_screen.presentation.viewmodel.MainScreenStates
import com.adyen.android.assignment.features.main_screen.presentation.viewmodel.MainScreenViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainScreenFragment :
    BaseFragment<FragmentMainScreenBinding, MainScreenStates, MainScreenActions>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainScreenBinding
        get() = FragmentMainScreenBinding::inflate

    override val viewModel: MainScreenViewModel by viewModels()

    override fun setupOnViewCreated() {}

    override fun handleViewState(it: MainScreenStates) {
        when(it){
            is MainScreenStates.InitialState -> viewModel.dispatch(MainScreenActions.LoadPictures(10))
        }
    }
}
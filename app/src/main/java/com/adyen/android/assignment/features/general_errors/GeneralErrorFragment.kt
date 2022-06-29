package com.adyen.android.assignment.features.general_errors

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import com.adyen.android.assignment.common.*
import com.adyen.android.assignment.common.ui.BaseFragment
import com.adyen.android.assignment.databinding.FragmentGeneralErrorBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GeneralErrorFragment :
    BaseFragment<FragmentGeneralErrorBinding, ViewState, Action, ViewEvents, Results>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentGeneralErrorBinding
        get() = FragmentGeneralErrorBinding::inflate

    lateinit var clickAction: () -> Unit

    override fun setupOnViewCreated() {
        arguments?.let {
            val args = it.get(ERROR_NAVIGATION_ARGUMENTS) as ArgumentData
            clickAction = args.onClick
            bindData(args)
        }

        binding.actionButton.setOnClickListener {
            clickAction()
            navigateUp()
        }

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                clickAction()
                navigateUp()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    private fun bindData(args: ArgumentData) {
        binding.actionButton.text = args.actionBtnText
        binding.mainMessage.text = args.mainText
        binding.subTitle.text = args.subTitle
    }

    override val viewModel: GeneralErrorViewModel by viewModels() //TODO remove viewmodel
    override fun handleViewState(it: ViewState) {}


}
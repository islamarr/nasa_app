package com.adyen.android.assignment.features.general_errors

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResult
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

    private var typeId: Int = 0

    override fun setupOnViewCreated() {
        arguments?.let { //TODO code refactor
            val args = it.get(ERROR_NAVIGATION_ARGUMENTS) as ArgumentData
            typeId = args.typeId
            bindData(args)
        }

        binding.actionButton.setOnClickListener {
            when (typeId) {
                0 -> setFragmentResult(ERROR_KEY, Bundle())
                1 -> {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        startActivity(Intent(Settings.Panel.ACTION_INTERNET_CONNECTIVITY)) //TODO onBack should click twice
                    } else {
                        Intent(Intent.ACTION_MAIN).apply {
                            setClassName(
                                "com.android.phone",
                                "com.android.phone.NetworkSetting"
                            )
                            startActivity(this)
                        }
                    }
                }
            }
            navigateUp()
        }

    }

    private fun bindData(args: ArgumentData) {
        binding.actionButton.text = args.actionBtnText
        binding.mainMessage.text = args.mainText
        binding.subTitle.text = args.subTitle
    }

    override val viewModel: GeneralErrorViewModel by viewModels() //TODO remove viewmodel
    override fun handleViewState(it: ViewState) {}


}
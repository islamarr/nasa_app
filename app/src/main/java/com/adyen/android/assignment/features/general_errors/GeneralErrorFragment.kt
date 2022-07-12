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
import com.adyen.android.assignment.common.data.NetworkUtils
import com.adyen.android.assignment.common.ui.BaseFragment
import com.adyen.android.assignment.databinding.FragmentGeneralErrorBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class GeneralErrorFragment :
    BaseFragment<FragmentGeneralErrorBinding, ViewState, Action, ViewEvents, Results>() {

    override val viewModel: GeneralErrorViewModel by viewModels()
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentGeneralErrorBinding
        get() = FragmentGeneralErrorBinding::inflate

    @Inject
    lateinit var networkUtils: NetworkUtils

    private var typeId: Int = 0

    override fun setupOnViewCreated() {
        handleArguments()
        handleClickListener()
        observeNetworkStatus()
    }

    private fun handleArguments() {
        arguments?.let {
            val args = it.get(ERROR_NAVIGATION_ARGUMENTS) as ArgumentData
            typeId = args.typeId
            bindData(args)
        }
    }

    private fun handleClickListener() {
        binding.actionButton.setOnClickListener {
            when (typeId) {
                0 -> handleBack()
                1 -> navigateToNetworkSettings()
            }
        }
    }

    private fun navigateToNetworkSettings() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            startActivity(Intent(Settings.Panel.ACTION_INTERNET_CONNECTIVITY))
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

    private fun observeNetworkStatus() {
        networkUtils.getNetworkStatus()
        networkUtils.networkLiveData.observe(viewLifecycleOwner) {
            if (it) {
                handleBack()
            }
        }
    }


    private fun handleBack() {
        setFragmentResult(ERROR_KEY, Bundle())
        navigateUp()
    }

    private fun bindData(args: ArgumentData) {
        binding.actionButton.text = args.actionBtnText
        binding.mainMessage.text = args.mainText
        binding.subTitle.text = args.subTitle
    }

}
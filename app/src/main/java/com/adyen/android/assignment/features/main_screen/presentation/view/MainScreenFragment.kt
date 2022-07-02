package com.adyen.android.assignment.features.main_screen.presentation.view

import android.app.Dialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.adyen.android.assignment.R
import com.adyen.android.assignment.common.ERROR_KEY
import com.adyen.android.assignment.common.ERROR_NAVIGATION_ARGUMENTS
import com.adyen.android.assignment.common.navigateUp
import com.adyen.android.assignment.common.ui.BaseFragment
import com.adyen.android.assignment.databinding.FragmentMainScreenBinding
import com.adyen.android.assignment.databinding.ReorderDialogBinding
import com.adyen.android.assignment.features.general_errors.ArgumentData
import com.adyen.android.assignment.features.main_screen.presentation.viewmodel.*
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainScreenFragment :
    BaseFragment<FragmentMainScreenBinding, MainScreenStates, MainScreenActions, MainScreenEvents, MainScreenResults>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainScreenBinding
        get() = FragmentMainScreenBinding::inflate

    override val viewModel: MainScreenViewModel by viewModels()

    private lateinit var argumentData: ArgumentData

    @Inject
    lateinit var planetaryAdapter: PlanetaryAdapter

    override fun setupOnViewCreated() {
        initRecyclerView()
        setScrollListener()
        observeEvents()
        setFragmentResultListener(ERROR_KEY) { _, _ ->
            loadAstronomyPicture()
        }
        binding.reorderBtn.setOnClickListener {
            showDialog()
        }
    }

    private fun showDialog() {
        var selectionId = viewModel.orderTypeSelected
        val dialogBinding = ReorderDialogBinding.inflate(LayoutInflater.from(context))
        Dialog(requireActivity()).apply {
            setCancelable(false)
            setContentView(dialogBinding.root)
            show()

            dialogBinding.radioGroup.apply {
                if (selectionId != -1)
                    check(dialogBinding.radioGroup.getChildAt(selectionId).id)
                setOnCheckedChangeListener { _, checkedId ->
                    selectionId = when (checkedId) {
                        R.id.reorderByTitleRadio -> 0
                        R.id.reorderByDateRadio -> 1
                        else -> -1
                    }
                }
            }

            dialogBinding.applyBtn.setOnClickListener {
                viewModel.orderTypeSelected = selectionId
                sortAstronomyPicture(selectionId)
                dismiss()
            }

            dialogBinding.resetBtn.setOnClickListener {
                dialogBinding.radioGroup.clearCheck()
                selectionId = -1
            }

        }
    }

    private fun observeEvents() {
        initViewEvents {
            when (it) {
                is MainScreenEvents.NavigateToErrorScreen ->
                    argumentData = ArgumentData(
                        getString(R.string.error_message_title),
                        getString(R.string.error_message_subtitle),
                        getString(R.string.refresh),
                        0
                    )
                is MainScreenEvents.NavigateToNoInternetScreen ->
                    argumentData = ArgumentData(
                        getString(R.string.no_internet),
                        getString(R.string.no_internet_subtitle),
                        getString(R.string.network_settings),
                        1
                    )

            }
            val bundle = bundleOf(ERROR_NAVIGATION_ARGUMENTS to argumentData)
            findNavController()
                .navigate(R.id.action_mainScreenFragment_to_generalErrorFragment, bundle)
        }
    }

    private fun initRecyclerView() {
        binding.astronomyList.adapter = planetaryAdapter
    }

    private fun setScrollListener() {
        binding.astronomyList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    binding.reorderBtn.extend()
                }
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0 || dy < 0 && binding.reorderBtn.isExtended) {
                    binding.reorderBtn.shrink()
                }
            }
        })
    }

    private fun loadAstronomyPicture() {
        viewModel.dispatch(MainScreenActions.LoadAstronomyPicture)
    }

    private fun sortAstronomyPicture(sortType: Int) {
        viewModel.dispatch(MainScreenActions.SortList(sortType))
    }

    private fun showEmptyList(show: Boolean) {
        binding.apply {
            loading.isGone = true
            resultStatusText.isVisible = show
            astronomyList.isVisible = !show
        }
    }

    override fun handleViewState(it: MainScreenStates) {
        when (it) {
            is MainScreenStates.InitialState -> loadAstronomyPicture() //TODO move it to viewmodel
            is MainScreenStates.Loading -> binding.loading.isVisible = true
            is MainScreenStates.AstronomyListLoaded -> {
                showEmptyList(false)
                planetaryAdapter.submitList(it.astronomyPictureList)
            }
            is MainScreenStates.EmptyList -> {
                showEmptyList(true)
                binding.resultStatusText.text = getString(R.string.empty_list_message)
            }
        }
    }
}
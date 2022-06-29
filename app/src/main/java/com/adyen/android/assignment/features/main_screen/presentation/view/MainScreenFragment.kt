package com.adyen.android.assignment.features.main_screen.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.adyen.android.assignment.common.ui.BaseFragment
import com.adyen.android.assignment.databinding.FragmentMainScreenBinding
import com.adyen.android.assignment.features.main_screen.presentation.viewmodel.MainScreenActions
import com.adyen.android.assignment.features.main_screen.presentation.viewmodel.MainScreenStates
import com.adyen.android.assignment.features.main_screen.presentation.viewmodel.MainScreenViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainScreenFragment :
    BaseFragment<FragmentMainScreenBinding, MainScreenStates, MainScreenActions>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainScreenBinding
        get() = FragmentMainScreenBinding::inflate

    override val viewModel: MainScreenViewModel by viewModels()

    @Inject
    lateinit var planetaryAdapter: PlanetaryAdapter

    override fun setupOnViewCreated() {
        initRecyclerView()
        setScrollListener()
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

    private fun showEmptyList(show: Boolean) {
        binding.loading.isGone = true
        binding.resultStatusText.isVisible = show
        binding.astronomyList.isVisible = !show
    }

    override fun handleViewState(it: MainScreenStates) {
        when (it) {
            is MainScreenStates.InitialState -> loadAstronomyPicture()
            is MainScreenStates.Loading -> binding.loading.isVisible = true
            is MainScreenStates.AstronomyListLoaded -> {
                showEmptyList(false)
                planetaryAdapter.submitList(it.astronomyPictureList)
            }
            is MainScreenStates.EmptyList -> {
                showEmptyList(true)
                binding.resultStatusText.text = "getString(R.string.app_name)"
            }
            is MainScreenStates.ShowErrorMessage -> {
                showEmptyList(true)
                binding.resultStatusText.text = "getString(R.string.error_message)"
            }
            is MainScreenStates.NoInternetConnection -> {
                showEmptyList(true)
                binding.resultStatusText.text = "getString(R.string.error_message)"
            }
        }
    }
}
package com.adyen.android.assignment.features.details

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.adyen.android.assignment.R
import com.adyen.android.assignment.common.*
import com.adyen.android.assignment.common.ui.BaseFragment
import com.adyen.android.assignment.databinding.FragmentDetailsBinding
import com.adyen.android.assignment.features.general_errors.GeneralErrorViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment :
    BaseFragment<FragmentDetailsBinding, ViewState, Action, ViewEvents, Results>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDetailsBinding
        get() = FragmentDetailsBinding::inflate
    override val viewModel: GeneralErrorViewModel by viewModels()

    private val args: DetailsFragmentArgs by navArgs()

    override fun setupOnViewCreated() {
        val astronomy = args.astronomyPicture
        binding.titleText.text = astronomy.title
        binding.dateText.text = astronomy.date
        binding.explanation.text = astronomy.explanation
        astronomy.hdUrl?.let {
            loadImage(it)
        }
        binding.backBtn.setOnClickListener {
            navigateUp()
        }
    }

    private fun loadImage(url: String) {
        Glide.with(requireContext()).load(url)
            .placeholder(R.drawable.ic_placeholder)
            .transition(DrawableTransitionOptions.withCrossFade())
            .thumbnail(IMAGE_SIZE_MULTIPLIER)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(binding.coverImage)
    }


    override fun handleViewState(it: ViewState) {}
}
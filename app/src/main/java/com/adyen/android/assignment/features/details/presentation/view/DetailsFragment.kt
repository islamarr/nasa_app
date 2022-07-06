package com.adyen.android.assignment.features.details.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.adyen.android.assignment.R
import com.adyen.android.assignment.common.IMAGE_SIZE_MULTIPLIER
import com.adyen.android.assignment.common.navigateUp
import com.adyen.android.assignment.common.ui.BaseFragment
import com.adyen.android.assignment.databinding.FragmentDetailsBinding
import com.adyen.android.assignment.features.details.presentation.viewmodel.*
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment :
    BaseFragment<FragmentDetailsBinding, DetailsStates, DetailsActions, DetailsEvents, DetailsResults>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDetailsBinding
        get() = FragmentDetailsBinding::inflate
    override val viewModel: DetailsViewModel by viewModels()

    private val args: DetailsFragmentArgs by navArgs()
    private var isFavorite: Boolean = false

    override fun setupOnViewCreated() {
        val astronomy = args.astronomyPicture
        binding.apply {
            titleText.text = astronomy.title
            dateText.text = astronomy.date
            explanation.text = astronomy.explanation
            backBtn.setOnClickListener {
                navigateUp()
            }
        }
        astronomy.url?.let {
            loadImage(it)
        }

        binding.favoriteBtn.setOnClickListener {
            isFavorite = !isFavorite
            viewModel.dispatch(DetailsActions.SetFavoriteAction(isFavorite, astronomy))
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


    override fun handleViewState(it: DetailsStates) {
        when (it) {
            is DetailsStates.SavedState -> {
                isFavorite = it.isSaved
                binding.favoriteBtn.isChecked = isFavorite
            }
            is DetailsStates.InitialState -> viewModel.dispatch(
                DetailsActions.CheckAstronomyPictureFavorite(
                    args.astronomyPicture.title ?: "", args.astronomyPicture.date ?: ""
                )
            )
        }
    }
}
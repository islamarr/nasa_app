package com.adyen.android.assignment.features.main_screen.presentation.view

import androidx.recyclerview.widget.DiffUtil
import com.adyen.android.assignment.features.main_screen.domain.entities.AstronomyPicture

class PlanetaryDiffUtil : DiffUtil.ItemCallback<AstronomyPicture>() {

    override fun areItemsTheSame(oldItem: AstronomyPicture, newItem: AstronomyPicture) =
        oldItem == newItem //TODO edit this

    override fun areContentsTheSame(oldItem: AstronomyPicture, newItem: AstronomyPicture) =
        oldItem == newItem
}
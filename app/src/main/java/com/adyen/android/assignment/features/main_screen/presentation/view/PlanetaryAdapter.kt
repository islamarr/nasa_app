package com.adyen.android.assignment.features.main_screen.presentation.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.adyen.android.assignment.R
import com.adyen.android.assignment.common.IMAGE_SIZE_MULTIPLIER
import com.adyen.android.assignment.databinding.OneItemPlanetaryBinding
import com.adyen.android.assignment.features.main_screen.domain.entities.AstronomyPicture
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import javax.inject.Inject

class PlanetaryAdapter @Inject constructor() :
    ListAdapter<AstronomyPicture, PlanetaryAdapter.ViewHolder<OneItemPlanetaryBinding>>(
        PlanetaryDiffUtil()
    ) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder<OneItemPlanetaryBinding> {
        return ViewHolder(
            OneItemPlanetaryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder<OneItemPlanetaryBinding>, position: Int) {
        val listItems = getItem(position)
        bind(holder.binding, listItems, holder.itemView)
    }

    private fun bind(viewBinding: ViewBinding, item: AstronomyPicture, view: View) {
        val binding = viewBinding as OneItemPlanetaryBinding
        item.url?.let {
            loadImage(view.context, it, binding.image)
        }
        binding.date.text = item.date
        binding.title.text = item.title
        view.rootView.setOnClickListener {
            it.findNavController().navigate(
                MainScreenFragmentDirections.actionMainScreenFragmentToDetailsFragment(item)
            )
        }
    }

    inner class ViewHolder<out T : ViewBinding>(val binding: OneItemPlanetaryBinding) :
        RecyclerView.ViewHolder(binding.root)

    private fun loadImage(context: Context, url: String, imageView: ImageView) {
        Glide.with(context).load(url)
            .placeholder(R.drawable.ic_placeholder)
            .transition(DrawableTransitionOptions.withCrossFade())
            .thumbnail(IMAGE_SIZE_MULTIPLIER)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(imageView)
    }


}

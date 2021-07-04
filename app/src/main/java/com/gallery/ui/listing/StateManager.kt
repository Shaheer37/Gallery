package com.gallery.ui.listing

import com.gallery.R
import com.gallery.databinding.FragmentGalleryBinding
import com.gallery.ui.view.gone
import com.gallery.ui.view.visible

object StateManager {


    fun showNoImagesFound(binding: FragmentGalleryBinding) {
        binding.recyclerView.gone()
        binding.loaderContainer.gone()
        binding.errorContainer.visible()
        binding.ivError.setImageResource(R.drawable.ic_not_found)
        binding.tvError.text = binding.root.context.getString(R.string.no_images_found)
    }


    fun showSuccessfullyLoaded(binding: FragmentGalleryBinding) {
        binding.recyclerView.visible()
        binding.loaderContainer.gone()
        binding.errorContainer.gone()
    }


    fun showLoading(binding: FragmentGalleryBinding) {
        binding.recyclerView.gone()
        binding.loaderContainer.visible()
        binding.errorContainer.gone()
    }


    fun showErrorLoading(binding: FragmentGalleryBinding) {
        binding.recyclerView.gone()
        binding.loaderContainer.gone()
        binding.errorContainer.visible()
        binding.ivError.setImageResource(R.drawable.ic_no_wifi)
        binding.tvError.text = binding.root.context.getString(R.string.please_connect)
    }
}
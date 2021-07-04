package com.gallery.ui.listing.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gallery.data.model.PhotoData
import com.gallery.databinding.ItemImageBinding
import com.gallery.ui.listing.GalleryViewModel


class ImageViewHolder(private val binding: ItemImageBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        viewModel: GalleryViewModel,
        photo: PhotoData
    ) {

        binding.ivPhoto.aspectRatio = photo.previewHeight.toDouble() / photo.previewWidth
        binding.ivPhoto.setOnClickListener { viewModel.previewImage(photo) }

        val context = binding.root.context

        Glide.with(context).asBitmap()
            .load(photo.previewUrl)
            .into(binding.ivPhoto)
    }


    companion object {

        fun instance(parent: ViewGroup): ImageViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemImageBinding.inflate(layoutInflater, parent, false)

            return ImageViewHolder(binding)
        }


    }
}
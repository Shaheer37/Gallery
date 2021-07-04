package com.gallery.ui.listing.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.gallery.data.model.PhotoData
import com.gallery.ui.listing.GalleryViewModel

class Adapter(private val viewModel: GalleryViewModel) :
    PagingDataAdapter<PhotoData, ImageViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ImageViewHolder.instance(parent)

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(viewModel, it) }
    }

    companion object COMPARATOR : DiffUtil.ItemCallback<PhotoData>() {
        override fun areContentsTheSame(
            oldItem: PhotoData,
            newItem: PhotoData
        ): Boolean =
            oldItem.id == newItem.id

        override fun areItemsTheSame(oldItem: PhotoData, newItem: PhotoData): Boolean =
            oldItem == newItem
    }
}
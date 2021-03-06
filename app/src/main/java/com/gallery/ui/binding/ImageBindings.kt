package com.gallery.ui.binding

import android.widget.ImageView
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.gallery.R
import com.gallery.data.model.PhotoData
import com.gallery.ui.preview.PreviewViewModel

@BindingAdapter(value = ["previewImage", "viewModel"], requireAll = true)
fun previewImage(imageView: ImageView, photoData: PhotoData?, viewModel: PreviewViewModel) {
    photoData?.let { photoData ->
        val circularProgressDrawable = CircularProgressDrawable(imageView.context)
        circularProgressDrawable.setColorSchemeColors(ResourcesCompat.getColor(imageView.context.resources, R.color.colorOnPrimary, null))
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()

        Glide.with(imageView.context)
            .load(photoData.imageUrl)
            .placeholder(circularProgressDrawable)
            .into(imageView)
    }

}
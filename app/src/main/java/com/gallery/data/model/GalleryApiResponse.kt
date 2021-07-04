package com.gallery.data.model

import kotlinx.serialization.Serializable

@Serializable
data class GalleryApiResponse(
    val total: Int,
    val totalHits: Int,
    val hits: List<PhotoData>
)
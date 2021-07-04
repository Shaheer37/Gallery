package com.gallery.data

import com.gallery.data.model.GalleryApiResponse
import com.gallery.data.model.Resource

interface DataRepository {
    suspend fun getPhotos(
        query: String,
        page: Int,
        perPage: Int
    ): Resource<GalleryApiResponse>
}
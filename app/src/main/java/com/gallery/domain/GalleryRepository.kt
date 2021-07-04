package com.gallery.domain

import androidx.paging.PagingData
import com.gallery.data.model.GalleryApiResponse
import com.gallery.data.model.PhotoData
import kotlinx.coroutines.flow.Flow

interface GalleryRepository {
    fun getPhotos(query: String): Flow<PagingData<PhotoData>>
}
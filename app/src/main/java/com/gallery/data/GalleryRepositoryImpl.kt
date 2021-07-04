package com.gallery.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.gallery.data.remote.GalleryApi
import com.gallery.data.model.GalleryApiResponse
import com.gallery.data.model.PhotoData
import com.gallery.domain.GalleryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GalleryRepositoryImpl @Inject constructor(
    private val dataRepository: DataRepository
): GalleryRepository {
    override fun getPhotos(query: String): Flow<PagingData<PhotoData>> {
        return Pager(
            config = PagingConfig(
                pageSize = GalleryApi.DEFAULT_IMAGES_PER_PAGE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { DataSource(dataRepository, query) }
        ).flow
    }
}
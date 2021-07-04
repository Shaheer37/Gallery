package com.gallery.data.remote

import com.gallery.data.DataRepository
import com.gallery.data.model.GalleryApiResponse
import com.gallery.data.model.PhotoData
import com.gallery.data.model.Resource
import com.gallery.utils.network.getErrorMessage
import com.gallery.utils.network.getNetworkErrorMessage
import javax.inject.Inject

class RemoteDataRepository @Inject constructor(
    private val api: GalleryApi
): DataRepository {
    override suspend fun getPhotos(query: String, page: Int, perPage: Int): Resource<GalleryApiResponse> {
        return try {
            val response = api.getPhotos(
                query = query,
                page = page,
                perPage = perPage
            )
            if (response.isSuccessful) Resource.Valid(response.body()!!)
            else Resource.Invalid(getErrorMessage(response.code()))
        } catch (e: Exception) {
            Resource.Invalid(getNetworkErrorMessage(e))
        }
    }
}
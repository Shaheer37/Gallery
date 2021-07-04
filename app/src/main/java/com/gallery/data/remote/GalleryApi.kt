package com.gallery.data.remote

import com.gallery.BuildConfig
import com.gallery.data.model.GalleryApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface GalleryApi {
    companion object{
        const val INITIAL_KEY = 1
        const val DEFAULT_IMAGES_PER_PAGE = 20
    }

    @GET
    suspend fun getPhotos(
        @Url url: String = BuildConfig.ImageApiBaseUrl,
        @Query("key") apiKey: String = BuildConfig.ImageApiKey,
        @Query("q") query: String,
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = DEFAULT_IMAGES_PER_PAGE,
        @Query("safesearch") safeSearch: Boolean = true
    ): Response<GalleryApiResponse>
}
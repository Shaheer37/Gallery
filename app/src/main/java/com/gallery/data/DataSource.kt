package com.gallery.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.gallery.data.remote.GalleryApi
import com.gallery.data.model.PhotoData
import com.gallery.data.model.Resource
import retrofit2.HttpException
import java.io.IOException

class DataSource(
    private val dataRepository: DataRepository,
    private val query: String
): PagingSource<Int, PhotoData>() {

    override fun getRefreshKey(state: PagingState<Int, PhotoData>): Int?
        = state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PhotoData> {
        val position = params.key ?: GalleryApi.INITIAL_KEY
        val apiQuery = query
        return try {
            val response = dataRepository.getPhotos(
                    query = apiQuery,
                    page = position,
                    perPage = params.loadSize
                )
            when(response){
                is Resource.Valid -> {
                    val nextKey = if (response.data.hits.isEmpty()) null else position+1
                    LoadResult.Page(
                        data = response.data.hits,
                        prevKey = if (position == GalleryApi.INITIAL_KEY) null else position-1,
                        nextKey = nextKey
                    )
                }
                is Resource.Invalid -> {
                    LoadResult.Error(Exception(response.message))
                }
            }
        } catch (exception: IOException) { LoadResult.Error(exception) }
        catch (exception: HttpException) { LoadResult.Error(exception) }
    }
}
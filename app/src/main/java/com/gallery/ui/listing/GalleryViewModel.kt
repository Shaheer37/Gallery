package com.gallery.ui.listing

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.gallery.data.model.PhotoData
import com.gallery.domain.GalleryRepository
import com.gallery.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val repository: GalleryRepository
): ViewModel(), SearchClearer {

    private val _previewImage = MutableLiveData<Event<PhotoData>>()
    val previewImage: LiveData<Event<PhotoData>> = _previewImage

    private val _searchQuery = MutableLiveData("")
    val searchQuery: LiveData<String> = _searchQuery

    val imagesList = searchQuery.switchMap {
        getImages(it).asLiveData()
    }

    private fun getImages(queryString: String): Flow<PagingData<PhotoData>> {
        return repository.getPhotos(queryString).cachedIn(viewModelScope)
    }

    fun onSearchQueryUpdated(query: String) {
        _searchQuery.postValue(query)
    }

    override fun clearSearch() {
        _searchQuery.postValue("")
    }

    fun retrySearch() {
        _searchQuery.postValue(_searchQuery.value)
    }

    fun previewImage(photo: PhotoData) {
        _previewImage.postValue(Event(photo))
    }
}
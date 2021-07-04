package com.gallery.ui.preview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gallery.data.model.PhotoData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PreviewViewModel @Inject constructor(): ViewModel() {

    private val _photo = MutableLiveData<PhotoData>()
    val photo: LiveData<PhotoData> = _photo

    fun setPhoto(photo: PhotoData){
        _photo.postValue(photo)
    }
}
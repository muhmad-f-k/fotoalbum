package com.example.fotoalbum.full_photo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fotoalbum.model.Photo
import com.example.fotoalbum.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class FullPhotoViewModel(private val repository: Repository) : ViewModel() {
    val myResponse: MutableLiveData<Response<Photo>> = MutableLiveData()
    val deleteResponse: MutableLiveData<Response<Photo>> = MutableLiveData()

    val _photoId = MutableLiveData<Int>()
    val _photoTitle = MutableLiveData<String>()

    fun getFullPhoto(photoId: Int){
        viewModelScope.launch {
            val response = repository.getFullPhoto(photoId)
            myResponse.value = response
            _photoId.value = photoId
        }
    }

    fun deletePhoto(photoId: Int) {
        viewModelScope.launch {
            val response = repository.deletePhoto(photoId)
            deleteResponse.value = response
        }
    }
}
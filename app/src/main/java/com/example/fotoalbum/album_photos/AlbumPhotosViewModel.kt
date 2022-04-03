package com.example.fotoalbum.album_photos

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fotoalbum.model.Photo
import com.example.fotoalbum.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class AlbumPhotosViewModel(private val repository: Repository) : ViewModel() {

    val myResponse: MutableLiveData<Response<List<Photo>>> = MutableLiveData()

    fun getPhotos(albumId: Int){
        viewModelScope.launch {
            val response = repository.getPhotos(albumId)
            myResponse.value = response
        }
    }
}
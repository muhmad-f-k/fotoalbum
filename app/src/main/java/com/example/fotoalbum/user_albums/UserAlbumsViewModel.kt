package com.example.fotoalbum.user_albums

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fotoalbum.model.Album
import com.example.fotoalbum.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class UserAlbumsViewModel(private val repository: Repository) : ViewModel() {

    val myResponse: MutableLiveData<Response<List<Album>>> = MutableLiveData()

    fun getAlbums(userId: Int){
        viewModelScope.launch {
            val response = repository.getAlbums(userId)
            myResponse.value = response
        }
    }
}
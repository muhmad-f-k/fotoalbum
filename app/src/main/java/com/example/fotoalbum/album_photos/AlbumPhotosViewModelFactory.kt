package com.example.fotoalbum.album_photos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fotoalbum.repository.Repository

class AlbumPhotosViewModelFactory(private val repository: Repository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AlbumPhotosViewModel(repository) as T
    }
}
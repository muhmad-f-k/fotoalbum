package com.example.fotoalbum.full_photo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fotoalbum.full_photo.FullPhotoViewModel
import com.example.fotoalbum.repository.Repository

class FullPhotoViewModelFactory(private val repository: Repository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FullPhotoViewModel(repository) as T
    }
}
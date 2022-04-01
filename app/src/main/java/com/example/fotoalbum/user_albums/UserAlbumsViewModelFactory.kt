package com.example.fotoalbum.user_albums

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fotoalbum.overview.OverviewViewModel
import com.example.fotoalbum.repository.Repository

class UserAlbumsViewModelFactory(private val repository: Repository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserAlbumsViewModel(repository) as T
    }

}
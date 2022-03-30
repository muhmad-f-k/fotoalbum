package com.example.fotoalbum.overview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fotoalbum.model.User
import com.example.fotoalbum.repository.Repository
import kotlinx.coroutines.launch

class OverviewViewModel(private val repository: Repository) : ViewModel() {

    val myResponse: MutableLiveData<User> = MutableLiveData()

    fun getUsers(){
        viewModelScope.launch {
            val response = repository.getUsers()
            myResponse.value = response
        }
    }
}
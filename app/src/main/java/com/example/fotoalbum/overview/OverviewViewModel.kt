package com.example.fotoalbum.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fotoalbum.network.JsonApi
import com.example.fotoalbum.network.User
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import java.lang.Exception
import java.util.ArrayList
import javax.security.auth.callback.Callback

class OverviewViewModel : ViewModel() {

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>>
        get() = _users

    private val _navigateToSelectedUser = MutableLiveData<User>()
    val navigateToSelectedUser: LiveData<User>
        get() = _navigateToSelectedUser

    init {
        getUsers()
    }

    private fun getUsers() {
        viewModelScope.launch {
            try {
                _users.value = JsonApi.retrofitService.getUsers()
            }catch (e: Exception) {
                _users.value = ArrayList()
            }
        }
    }
}
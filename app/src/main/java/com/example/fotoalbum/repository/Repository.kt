package com.example.fotoalbum.repository

import com.example.fotoalbum.model.User
import com.example.fotoalbum.api.RetrofitInstance

class Repository {

    suspend fun getUsers() {
        RetrofitInstance.api.getUsers()
    }
}
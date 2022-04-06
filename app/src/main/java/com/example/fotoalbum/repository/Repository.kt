package com.example.fotoalbum.repository

import com.example.fotoalbum.model.User
import com.example.fotoalbum.api.RetrofitInstance
import com.example.fotoalbum.model.Album
import com.example.fotoalbum.model.Photo
import retrofit2.Response
import retrofit2.http.POST

class Repository {

    suspend fun getUsers(): Response<List<User>> {
        return RetrofitInstance.api.getUsers()
    }

    suspend fun getAlbums(userId: Int): Response<List<Album>> {
        return RetrofitInstance.api.getAlbums(userId)
    }

    suspend fun getPhotos(albumId: Int): Response<List<Photo>> {
        return RetrofitInstance.api.getPhotos(albumId)
    }

    suspend fun getFullPhoto(photoId: Int): Response<Photo> {
        return RetrofitInstance.api.getFullPhoto(photoId)
    }

    suspend fun deletePhoto(photoId: Int): Response<Photo> {
        return RetrofitInstance.api.deletePhoto(photoId)
    }

    suspend fun changeTitle(photoId: Int, photo: Photo): Response<Photo> {
        return RetrofitInstance.api.changeTitle(photoId, photo)
    }
}
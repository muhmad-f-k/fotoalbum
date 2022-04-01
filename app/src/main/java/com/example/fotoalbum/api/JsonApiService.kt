package com.example.fotoalbum.api

import com.example.fotoalbum.model.Album
import com.example.fotoalbum.model.User
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://jsonplaceholder.typicode.com"

interface SimpleApi {

    @GET("/users")
    suspend fun getUsers(): Response<List<User>>

    @GET("/albums")
    suspend fun getAlbums(
        @Query("userID") userId: Int
    ): Response<List<Album>>
}

object RetrofitInstance {
    private val retrofit by lazy{
        Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    }

    val api: SimpleApi by lazy {
        retrofit.create(SimpleApi::class.java)
    }
}
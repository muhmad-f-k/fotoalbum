package com.example.fotoalbum.api

import com.example.fotoalbum.model.Photo
import com.example.fotoalbum.model.User
import com.example.fotoalbum.model.Album
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

private const val BASE_URL = "https://jsonplaceholder.typicode.com"

interface SimpleApi {

    @GET("/users")
    suspend fun getUsers(): Response<List<User>>

    @GET("/albums")
    suspend fun getAlbums(
        @Query("userId") userId: Int
    ): Response<List<Album>>

    @GET("/photos")
    suspend fun getPhotos(
        @Query("albumId") albumId: Int
    ): Response<List<Photo>>

    @GET("/photos/{photoId}")
    suspend fun getFullPhoto(
        @Path("photoId") photoId: Int
    ): Response<Photo>

    @DELETE("/photos/{photoId}")
    suspend fun deletePhoto(
        @Path("photoId") photoId: Int
    ): Response<Photo>

    @POST("/photos/{photoId}")
    suspend fun changeTitle(
        @Path("photoId") photoId: Int,
        @Body post: POST
    ): Response<Photo>
}

object RetrofitInstance {
    private val retrofit by lazy{
        Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    }

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    val api: SimpleApi by lazy {
        retrofit.create(SimpleApi::class.java)
    }

}
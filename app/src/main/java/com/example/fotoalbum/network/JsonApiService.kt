package com.example.fotoalbum.network

import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://jsonplaceholder.typicode.com"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface JsonApiService {
    @GET("user")
    fun getUsers():
            Call<String>
}

object JsonApi {
    val retrofitService : JsonApiService by lazy { retrofit.create(JsonApiService::class.java) }
}
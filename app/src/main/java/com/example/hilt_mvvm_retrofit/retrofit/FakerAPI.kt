package com.example.hilt_mvvm_retrofit.retrofit

import com.example.hilt_mvvm_retrofit.models.Product
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface FakerAPI {

    @GET("{path}")
    suspend fun getProducts(@Path("path")string: String):Response<List<Product>>
}
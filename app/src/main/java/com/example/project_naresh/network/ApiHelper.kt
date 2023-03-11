package com.example.project_naresh.network

import com.example.project_naresh.data.ApiResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiHelper{

    private val retrofitBuilder = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
        .create(ApiInterface::class.java)

    suspend fun fetchData() : Response<ApiResponse> {
        return retrofitBuilder.getData()
    }
}
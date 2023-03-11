package com.example.project_naresh.network

import com.example.project_naresh.data.ApiResponse
import retrofit2.Response
import retrofit2.http.GET


const val BASE_URL = "https://archive.org/metadata/"
interface ApiInterface {

    @GET("TheAdventuresOfTomSawyer_201303")
    suspend fun getData(): Response<ApiResponse>
}
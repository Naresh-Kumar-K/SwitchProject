package com.example.project_naresh

import androidx.lifecycle.LiveData
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("TheAdventuresOfTomSawyer_201303")
    fun getData(): Call<LiveData<File>>
}
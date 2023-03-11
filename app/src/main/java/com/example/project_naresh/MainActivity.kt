package com.example.project_naresh

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project_naresh.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

var BASE_URL = "https://archive.org/metadata/"
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: UserRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData()
        retrofitData.enqueue(object : Callback<ApiResponse?> {
            override fun onResponse(
                call: Call<ApiResponse?>,
                response: Response<ApiResponse?>,
            ) {
                val responseBody = response.body()!!
                binding.rvOne.layoutManager = LinearLayoutManager(this@MainActivity)
                binding.rvOne.setHasFixedSize(true)
                adapter = UserRecyclerAdapter(responseBody.files)
                binding.rvOne.adapter = adapter
            }
            override fun onFailure(call: Call<ApiResponse?>, t: Throwable) {
                Log.d("This", "Failure".plus(t.message))
            }
        })
    }
}
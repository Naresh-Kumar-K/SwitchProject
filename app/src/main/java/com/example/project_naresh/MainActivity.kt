package com.example.project_naresh

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
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
        retrofitData.enqueue(object : Callback<LiveData<File>?> {
            override fun onResponse(
                call: Call<LiveData<File>?>,
                response: Response<LiveData<File>?>,
            ) {
                val responseBody = response.body()!!
                binding.rvOne.layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = UserRecyclerAdapter(responseBody as ArrayList<File>)
                Log.d("adapter", adapter.toString())
                binding.rvOne.adapter = adapter
            }

            override fun onFailure(call: Call<LiveData<File>?>, t: Throwable) {
                Log.d("This", "Failure".plus(t.message))
            }
        })
    }
}
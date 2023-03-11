package com.example.project_naresh.room_database

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.project_naresh.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FileRepo private constructor(private val fileDao: FileDao) {
    val fileList: LiveData<List<File>> = fileDao.getAllFiles()

    init {
        fetchData()
    }
    suspend fun addData(file: File) {
        fileDao.addFile(file)
    }

    suspend fun insertAll(file: List<File>) {
        fileDao.insertAll(*file.toTypedArray())
    }

    suspend fun updateData(file: File) {
        fileDao.update(file)
    }

    suspend fun deleteData(file: File) {
        fileDao.delete(file)
    }

    private fun fetchData() {
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
                CoroutineScope(Dispatchers.IO).launch {
                    insertAll(response.body()?.files.orEmpty())
                }
            }

            override fun onFailure(call: Call<ApiResponse?>, t: Throwable) {
                Log.d("This", "Failure".plus(t.message))
            }
        })
    }

    companion object {
        private var instance : FileRepo? = null
        fun getRepoInstance(application: Application): FileRepo {
            if(instance == null) {
                val fileDao = AppDatabase.getDatabase(application).getDataDao()
                instance = FileRepo(fileDao)
            }
            return instance!!
        }
    }
}
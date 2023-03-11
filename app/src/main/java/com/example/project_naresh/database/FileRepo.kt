package com.example.project_naresh.database

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.project_naresh.data.File
import com.example.project_naresh.network.ApiHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
        CoroutineScope(Dispatchers.IO).launch {
            insertAll(ApiHelper.fetchData().body()?.files.orEmpty())
        }
    }

    companion object {
        private var instance: FileRepo? = null
        fun getRepoInstance(application: Application): FileRepo {
            if (instance == null) {
                val fileDao = AppDatabase.getDatabase(application).getDataDao()
                instance = FileRepo(fileDao)
            }
            return instance!!
        }
    }
}
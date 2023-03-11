package com.example.project_naresh

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.project_naresh.room_database.AppDatabase
import com.example.project_naresh.room_database.FileRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    val fileList: LiveData<List<File>>
    private val repository: FileRepo

    init {
        val fileDao = AppDatabase.getDatabase(application).getDataDao()
        repository = FileRepo(fileDao)
        fileList = repository.fileList
    }

    fun addFile(data: File) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addData(data)
        }
    }

    fun insertAll(data: List<File>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertAll(data)
        }

    }

    fun updateFile(data: File) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateData(data)
        }
    }

    fun deleteFile(data: File) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteData(data)
        }
    }
}
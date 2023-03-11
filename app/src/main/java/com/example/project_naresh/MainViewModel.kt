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
    private val repository: FileRepo = FileRepo.getRepoInstance(application)

    init {
        fileList = repository.fileList
    }
}
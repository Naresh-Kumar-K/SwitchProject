package com.example.project_naresh.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.project_naresh.data.File
import com.example.project_naresh.database.FileRepo

class MainViewModel(application: Application) : AndroidViewModel(application) {
    val fileList: LiveData<List<File>>
    private val repository: FileRepo = FileRepo.getRepoInstance(application)

    init {
        fileList = repository.fileList
    }
}
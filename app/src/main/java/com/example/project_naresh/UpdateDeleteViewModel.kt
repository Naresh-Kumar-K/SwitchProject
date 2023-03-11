package com.example.project_naresh

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_naresh.room_database.FileRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UpdateDeleteViewModel(application: Application): AndroidViewModel(application) {
    private val repository: FileRepo = FileRepo.getRepoInstance(application)

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
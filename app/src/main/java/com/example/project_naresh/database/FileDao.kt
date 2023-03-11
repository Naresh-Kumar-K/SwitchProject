package com.example.project_naresh.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.project_naresh.data.File


@Dao
interface FileDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFile(file: File)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(vararg file: File)

    @Query("SELECT * FROM files ORDER BY name ASC")
    fun getAllFiles(): LiveData<List<File>>

    @Update
    suspend fun update(file: File)

    @Delete
    suspend fun delete(file: File)

}
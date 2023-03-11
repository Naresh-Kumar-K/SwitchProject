package com.example.project_naresh

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "files")
data class File(
    val btih: String?,
    val crc32: String?,
    val format: String?,
    @PrimaryKey
    val md5: String,
    val mtime: String?,
    val name: String?,
    val original: String?,
    val rotation: String?,
    val sha1: String?,
    val size: String?,
    val source: String?,
    val summation: String?,
)

data class ApiResponse(val files: List<File>)
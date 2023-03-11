package com.example.project_naresh.data

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "files")
data class File(
    val btih: String?,
    val crc32: String?,
    val format: String?,
    @PrimaryKey
    val md5: String,
    var mtime: String?,
    var name: String?,
    var original: String?,
    var rotation: String?,
    val sha1: String?,
    val size: String?,
    var source: String?,
    val summation: String?,
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString().orEmpty(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(btih)
        parcel.writeString(crc32)
        parcel.writeString(format)
        parcel.writeString(md5)
        parcel.writeString(mtime)
        parcel.writeString(name)
        parcel.writeString(original)
        parcel.writeString(rotation)
        parcel.writeString(sha1)
        parcel.writeString(size)
        parcel.writeString(source)
        parcel.writeString(summation)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<File> {
        override fun createFromParcel(parcel: Parcel): File {
            return File(parcel)
        }

        override fun newArray(size: Int): Array<File?> {
            return arrayOfNulls(size)
        }
    }
}

data class ApiResponse(val files: List<File>)
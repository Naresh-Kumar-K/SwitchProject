package com.example.project_naresh.room_database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.project_naresh.File


@Database(entities = [File::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getDataDao(): FileDao
    companion object{

        @Volatile
        private var Instant: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase{
            val tempInstant = Instant

            if(tempInstant != null){
                return tempInstant

            }
            synchronized(this){
                val instant = Room.databaseBuilder(
                    /* context = */ context.applicationContext,
                    /* klass = */ AppDatabase::class.java,
                    /* name = */ "user_table"
                ).build()
                Instant = instant
                return instant
            }
        }

    }
}
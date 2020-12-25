package com.damir.android.cv.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [VacancyDetailLocal::class, Resume::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun vacancyDao(): VacancyDao
    abstract fun resumeDao(): ResumeDao

    companion object {
        private lateinit var instance: AppDatabase

        fun init(context: Context) {
            instance = Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "AppDatabase"
            ).build()
        }

        fun getDb() = instance
    }
}
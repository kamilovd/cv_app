package com.damir.android.cv.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ResumeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveResume(resume: Resume)

    @Query("SELECT * FROM Resume WHERE user=:user")
    fun getResumeByUser(user: String): LiveData<Resume>
}
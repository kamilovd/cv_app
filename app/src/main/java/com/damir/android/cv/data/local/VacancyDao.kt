package com.damir.android.cv.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface VacancyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveVacancy(vacancy: VacancyDetailLocal)

    @Query("SELECT * FROM VacancyDetailLocal")
    fun getAllSavedVacancy(): LiveData<List<VacancyDetailLocal>>

    @Query("SELECT * FROM VacancyDetailLocal where id =:id")
    fun getVacancyById(id: String): LiveData<VacancyDetailLocal>
}
package com.damir.android.cv

import com.damir.android.cv.data.local.AppDatabase
import com.damir.android.cv.data.local.VacancyDao
import com.damir.android.cv.data.local.VacancyDetailLocal
import com.damir.android.cv.data.remote.HhClient
import com.damir.android.cv.data.remote.HhService

class VacancyRepository (
    private val api: HhService = HhClient.create(),
    private val dao: VacancyDao = AppDatabase.getDb().vacancyDao()
) {

    suspend fun loadVacancies(query: String = "Android") = api.getVacancies(query)
    suspend fun loadVacancyById(id: Long) = api.getVacancyById(id)
    suspend fun saveLocalVacancy(vacancy: VacancyDetailLocal) = dao.saveVacancy(vacancy)
    fun getAllSavedVacancy() = dao.getAllSavedVacancy()
    fun getVacancyById(id: String) = dao.getVacancyById(id)
}
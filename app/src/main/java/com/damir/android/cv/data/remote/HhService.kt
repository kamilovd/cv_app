package com.damir.android.cv.data.remote

import com.damir.android.cv.data.VacancyDetail
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HhService {

    @GET("vacancies")
    suspend fun getVacancies(@Query("text") text: String? = null): VacanciesResponse

    @GET("vacancies/{id}")
    suspend fun getVacancyById(@Path("id")id: Long): VacancyDetail
}
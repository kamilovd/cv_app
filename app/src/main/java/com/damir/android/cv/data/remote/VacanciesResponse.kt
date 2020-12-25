package com.damir.android.cv.data.remote

import com.damir.android.cv.data.Vacancy
import com.google.gson.annotations.SerializedName

data class VacanciesResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("pages")
    val pages: Int,
    @SerializedName("items")
    val items: List<Vacancy>
)
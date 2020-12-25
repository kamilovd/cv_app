package com.damir.android.cv.data

import com.google.gson.annotations.SerializedName

data class Vacancy (
    @SerializedName("id")
    val id: Long,
    @SerializedName("salary")
    val salary: Salary?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("area")
    val area: Area?,
    @SerializedName("published_at")
    val publishedAt: String?,
    @SerializedName("employer")
    val employer: Employer?,
    @SerializedName("contacts")
    val contacts: Contacts?,
    @SerializedName("address")
    val address: Address?,
    @SerializedName("snippet")
    val snippet: Snippet?,
    @SerializedName("schedule")
    val schedule: Schedule?
)
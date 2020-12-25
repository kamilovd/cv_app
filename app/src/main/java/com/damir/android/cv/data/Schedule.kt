package com.damir.android.cv.data

import com.google.gson.annotations.SerializedName

data class Schedule(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
)
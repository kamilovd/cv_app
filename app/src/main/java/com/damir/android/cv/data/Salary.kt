package com.damir.android.cv.data

import com.google.gson.annotations.SerializedName

data class Salary(
    @SerializedName("from")
    val from: Long?,
    @SerializedName("to")
    val to: Long?,
    @SerializedName("currency")
    val currency: String?
)
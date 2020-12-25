package com.damir.android.cv.data

import com.google.gson.annotations.SerializedName

data class Contacts(
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String
)
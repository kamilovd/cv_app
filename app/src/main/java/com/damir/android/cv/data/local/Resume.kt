package com.damir.android.cv.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Resume(
    @PrimaryKey
    val user: String,
    val name: String,
    val phone: String,
    val city: String,
    val speciality: String,
    val skills: String,
    val experience: String
)

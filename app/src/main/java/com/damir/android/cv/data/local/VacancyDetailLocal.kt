package com.damir.android.cv.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class VacancyDetailLocal(
    @PrimaryKey
    val id: String,
    val name: String?,
    val from: Long?,
    val to: Long?,
    val currency: String?,
    val city: String?,
    val raw: String?,
    val experienceName: String?,
    val contactName: String?,
    val contactsEmail: String?,
    val description: String?,
    val employerName: String?,
    val logo240: String?,
    val logo90: String?,
    val trusted: Boolean?
)
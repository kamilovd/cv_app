package com.damir.android.cv.data

import com.google.gson.annotations.SerializedName

data class Address(
    @SerializedName("city")
    val city: String,
    @SerializedName("street")
    val street: String,
    @SerializedName("building")
    val building: String,
    @SerializedName("raw")
    val raw: String,
    @SerializedName("metro")
    val metro: Metro?
)

data class Metro(
    @SerializedName("station_name")
    val stationName: String,
    @SerializedName("line_name")
    val lineName: String
)
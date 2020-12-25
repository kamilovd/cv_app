package com.damir.android.cv.data

import com.google.gson.annotations.SerializedName

data class Employer(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("logo_urls")
    val logoUrls: LogoUrls?,
    @SerializedName("trusted")
    val trusted: Boolean?
)

data class LogoUrls(
    @SerializedName("90")
    val logo90: String?,
    @SerializedName("240")
    val logo240: String?,
    @SerializedName("original")
    val original: String?
)
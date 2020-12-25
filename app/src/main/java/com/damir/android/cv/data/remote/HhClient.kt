package com.damir.android.cv.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object HhClient {

    private const val BASE_URL = "https://api.hh.ru/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(HttpClient.client)
        .build()

    fun create(): HhService = retrofit.create(HhService::class.java)
}
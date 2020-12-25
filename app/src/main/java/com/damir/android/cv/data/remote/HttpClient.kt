package com.damir.android.cv.data.remote

import okhttp3.OkHttpClient

object HttpClient {
    val client: OkHttpClient = OkHttpClient.Builder().apply {
        addInterceptor { chain ->
            val request = chain.request().newBuilder().addHeader("User-Agent", "CVApp/1.0")
            val url = chain.request().url().newBuilder().addQueryParameter("area", "160").build()
            request.url(url)
            chain.proceed(request.build())
        }
    }.build()
}
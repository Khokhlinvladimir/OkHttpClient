package com.okhttp.app

import com.google.gson.Gson
import com.okhttp.app.sources.Const
import com.okhttp.app.sources.base.OkHttpConfig
import com.okhttp.app.sources.base.OkHttpSourcesProvider
import com.okhttp.app.sources.base.SourcesProvider
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor

fun main() {

    val request = Request.Builder()
        .url("https://dark-sky.p.rapidapi.com/%7Blatitude%7D,%7Blongitude%7D?units=auto&lang=en")
        .get()
        .addHeader("X-RapidAPI-Key", "ca3478cbf3msh23da0f8b70b504dp1e05f3jsn049c9d93a77e")
        .addHeader("X-RapidAPI-Host", "dark-sky.p.rapidapi.com")
        .build()

    val response = client.newCall(request).execute()

    println(response)



}

private fun createOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(createLoggingInterceptor())
        .build()
}

/**
 * Log requests and responses to LogCat.
 */

private fun createLoggingInterceptor(): Interceptor {
    return HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)
}

object SourceProviderHolder {
    val sourcesProvider: SourcesProvider by lazy {
        val config = OkHttpConfig(
            baseUrl = Const.BASE_URL,
            client = createOkHttpClient(),
            gson = Gson()
        )
        OkHttpSourcesProvider(config)
    }
}
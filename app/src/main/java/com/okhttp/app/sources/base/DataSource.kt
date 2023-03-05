package com.okhttp.app.sources.base

interface DataSource {

    suspend fun data(key: String, host: String): String

}
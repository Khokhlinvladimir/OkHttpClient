package com.okhttp.app.data.network

import com.okhttp.app.data.entities.Data

interface DataSource {
    suspend fun getData(endpoint: String): List<Data>
}
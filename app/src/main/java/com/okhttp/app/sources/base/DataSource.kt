package com.okhttp.app.sources.base

import com.okhttp.app.sources.entities.Header

interface DataSource {

    suspend fun data(key1: Header, key2: Header): String

}
package com.okhttp.app.sources.base

import com.okhttp.app.sources.Const
import com.okhttp.app.sources.entities.Header
import com.okhttp.app.sources.entities.ResponseEntity
import okhttp3.Request
import javax.inject.Inject

class OkHttpDataSource @Inject constructor(
    config: OkHttpConfig
): BaseOkHttpSource(config), DataSource {

    override suspend fun data(key1: Header, key2: Header): String {
        val request = Request.Builder()
            .url(Const.BASE_URL)
            .get()
            .addHeader(key1.value, key1.name)
            .addHeader(key2.value, key2.name)
            .build()
        val response = client.newCall(request).suspendEnqueue()
        return response.parseJsonResponse<ResponseEntity>().token
    }
}
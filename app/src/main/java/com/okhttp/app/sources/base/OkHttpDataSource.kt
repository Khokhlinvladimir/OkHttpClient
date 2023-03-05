package com.okhttp.app.sources.base

class OkHttpDataSource(
    config: OkHttpConfig
): DataSource {

    override suspend fun data(key: String, host: String): String {
        return ""
    }
}
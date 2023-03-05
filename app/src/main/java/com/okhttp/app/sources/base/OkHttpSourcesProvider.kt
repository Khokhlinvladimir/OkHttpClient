package com.okhttp.app.sources.base

class OkHttpSourcesProvider(
    private val config: OkHttpConfig
) : SourcesProvider {

    override fun getDataSource(): DataSource {
        return OkHttpDataSource(config)
    }
}
package com.okhttp.app.sources.base

import com.okhttp.app.sources.base.DataSource

interface SourcesProvider {

    fun getDataSource(): DataSource

}
package com.okhttp.app.di

import com.okhttp.app.sources.base.DataSource
import com.okhttp.app.sources.base.OkHttpDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    abstract fun bindsDataSource(
        okHttpDataSource: OkHttpDataSource
    ): DataSource


}
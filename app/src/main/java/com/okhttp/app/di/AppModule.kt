package com.okhttp.app.di

import com.okhttp.app.data.network.DataSource
import com.okhttp.app.data.network.OkHttpDataSource
import com.okhttp.app.domain.CitiesApi
import com.okhttp.app.domain.PopulationCalculate
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

    @Binds
    abstract fun bindsCitiesApi(
        populationCalculate: PopulationCalculate
    ): CitiesApi

}
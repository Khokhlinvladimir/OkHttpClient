package com.okhttp.app.data.network

interface CitiesApi {

    suspend fun getCitiesLowPopulation(endpoint: String): List<String>
    suspend fun getCitiesHighPopulation(endpoint: String): List<String>
}
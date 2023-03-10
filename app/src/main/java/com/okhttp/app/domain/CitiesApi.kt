package com.okhttp.app.domain

import com.okhttp.app.data.entities.Data

interface CitiesApi {
     fun getCitiesLowPopulation(data: List<Data>): List<String>
     fun getCitiesHighPopulation(data: List<Data>): List<String>
}
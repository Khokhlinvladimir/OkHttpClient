package com.okhttp.app.data.entities

data class Data(
    val country: String,
    val countryCode: String,
    val id: Int,
    val latitude: Double,
    val longitude: Double,
    val name: String,
    val population: Int,
    val region: String,
    val regionCode: String,
    val wikiDataId: String
)
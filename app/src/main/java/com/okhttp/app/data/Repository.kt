package com.okhttp.app.data

import com.okhttp.app.data.entities.Data
import com.okhttp.app.data.network.CitiesApi
import com.okhttp.app.data.network.DataSource
import com.okhttp.app.data.network.exeption.BackendException
import com.okhttp.app.data.network.exeption.InvalidCredentialsException
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class Repository@Inject constructor(private val dataSource: DataSource): CitiesApi {

    suspend fun data(endpoint: String): List<Data> {
        val response = try {
            dataSource.getData(endpoint)
        } catch (e: Exception) {
            if (e is BackendException && e.code == 401) {
                // map 401 error for sign-in to InvalidCredentialsException
                throw InvalidCredentialsException(e)
            } else {
                throw e
            }
        }
      return response
    }

    override suspend fun getCitiesHighPopulation(endpoint: String): List<String> {

        val map = TreeMap<Int, String>()
        val list = ArrayList<String>()

        for (i in 0 until 10){
            map[data(endpoint)[i].population] = data(endpoint)[i].name
        }

        for ((index, value) in map.values.withIndex() ){
            if (index<=5){
                list.add(value)
            }

        }

        return list
    }

    override suspend fun getCitiesLowPopulation(endpoint: String): List<String> {

        val map = TreeMap<Int, String>()
        val list = ArrayList<String>()

        for (i in 0 until 10){
            map[data(endpoint)[i].population] = data(endpoint)[i].name
        }

        for ((index, value) in map.values.withIndex() ){
            if (index >5){
                list.add(value)
            }
        }

       return list
    }

}
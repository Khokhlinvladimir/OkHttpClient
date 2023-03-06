package com.okhttp.app.data

import android.util.Log
import com.okhttp.app.data.entities.Data
import com.okhttp.app.data.network.CitiesApi
import com.okhttp.app.data.network.DataSource
import com.okhttp.app.data.network.OkHttpDataSource
import com.okhttp.app.data.network.exeption.BackendException
import com.okhttp.app.data.network.exeption.InvalidCredentialsException
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class Repository@Inject constructor(private val dataSource: DataSource): CitiesApi {


    suspend fun data(endpoint: String): List<Data> {
        val response = try {
            dataSource.getData(endpoint)
        } catch (e: Exception) {
            if (e is BackendException && e.code == 403) {
                // map 401 error for sign-in to InvalidCredentialsException
                throw InvalidCredentialsException(e)
            } else {
                throw e
            }
        }
      return response
    }

    override fun getCitiesHighPopulation(data: List<Data>): List<String> {

        val map = TreeMap<Int, String>()
        val list = ArrayList<String>()

        for (i in 0 until 10){
            map[data[i].population] = data[i].name
        }

        for ((index, value) in map.values.withIndex() ){
            if (index<5){
                list.add(value)
            }
        }

        return list
    }

    override fun getCitiesLowPopulation(data: List<Data>): List<String> {

        val map = TreeMap<Int, String>()
        val list = ArrayList<String>()

        for (i in 0 until 10){
            map[data[i].population] = data[i].name
        }

        for ((index, value) in map.values.withIndex() ){
            if (index >=5){
                list.add(value)
            }
        }

        return list
    }


}
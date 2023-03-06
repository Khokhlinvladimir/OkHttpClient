package com.okhttp.app.domain

import com.okhttp.app.data.entities.Data
import java.util.*
import javax.inject.Inject

class PopulationCalculate @Inject constructor() : CitiesApi {

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
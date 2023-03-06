package com.okhttp.app.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.okhttp.app.data.Repository
import com.okhttp.app.data.entities.Data
import com.okhttp.app.data.entities.Header
import com.okhttp.app.data.network.CitiesApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import java.util.*
import javax.inject.Inject
import javax.security.auth.Destroyable
import kotlin.collections.ArrayList

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository): ViewModel(){

    private val _dataHighPopulation = MutableLiveData<List<String>>()
    val dataHighPopulation: LiveData<List<String>> get() = _dataHighPopulation
    private val _dataLowPopulation = MutableLiveData<List<String>>()
    val dataLowPopulation: LiveData<List<String>> get() = _dataLowPopulation
    private lateinit var data: List<Data>

     fun getPopulation(endpoint: String) = viewModelScope.launch(Dispatchers.Main) {
        data = repository.data(endpoint)
        _dataHighPopulation.value = repository.getCitiesHighPopulation(data)
        _dataLowPopulation.value = repository.getCitiesLowPopulation(data)
    }





}
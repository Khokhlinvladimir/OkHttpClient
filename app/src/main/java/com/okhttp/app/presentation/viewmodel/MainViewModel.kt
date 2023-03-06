package com.okhttp.app.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.okhttp.app.data.Repository
import com.okhttp.app.data.entities.Data
import com.okhttp.app.domain.CitiesApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository, private val population: CitiesApi): ViewModel(){

    private val _dataHighPopulation = MutableLiveData<List<String>>()
    val dataHighPopulation: LiveData<List<String>> get() = _dataHighPopulation
    private val _dataLowPopulation = MutableLiveData<List<String>>()
    val dataLowPopulation: LiveData<List<String>> get() = _dataLowPopulation
    private lateinit var data: List<Data>

     fun getPopulation(endpoint: String) = viewModelScope.launch(Dispatchers.Main) {
        data = repository.data(endpoint)
        _dataHighPopulation.value = population.getCitiesHighPopulation(data)
        _dataLowPopulation.value = population.getCitiesLowPopulation(data)
    }





}
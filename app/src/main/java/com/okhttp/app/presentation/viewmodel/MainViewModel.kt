package com.okhttp.app.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.okhttp.app.data.Repository
import com.okhttp.app.data.entities.Header
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository): ViewModel() {


    private val _dataHighPopulation = MutableLiveData<List<String>>()
    val dataHighPopulation: LiveData<List<String>> get() = _dataHighPopulation
    private val _dataLowPopulation = MutableLiveData<List<String>>()
    val dataLowPopulation: LiveData<List<String>> get() = _dataLowPopulation



    fun getHighPopulation(endpoint: String) = viewModelScope.launch {
        _dataHighPopulation.value = repository.getCitiesLowPopulation(endpoint)
    }

    fun getLowPopulation(endpoint: String) = viewModelScope.launch {
        _dataLowPopulation.value = repository.getCitiesLowPopulation(endpoint)
    }

    


}
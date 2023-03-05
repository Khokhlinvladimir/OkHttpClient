package com.okhttp.app.app

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.okhttp.app.sources.Repository
import com.okhttp.app.sources.entities.Header
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository): ViewModel() {


    private val _data = MutableLiveData<String>()
    val data: LiveData<String> get() = _data

    init {
        viewModelScope.launch {
            _data.value = repository.data(Header("", ""), Header("", ""))
        }
    }

    


}
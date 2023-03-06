package com.okhttp.app.data

import com.okhttp.app.data.entities.Data
import com.okhttp.app.data.network.DataSource
import com.okhttp.app.data.network.exeption.BackendException
import com.okhttp.app.data.network.exeption.InvalidCredentialsException
import javax.inject.Inject

class Repository@Inject constructor(private val dataSource: DataSource){

    suspend fun data(endpoint: String): List<Data> {
        val response = try {
            dataSource.getData(endpoint)
        } catch (e: Exception) {
            if (e is BackendException && e.code == 403) {
                // map 403 error for sign-in to InvalidCredentialsException
                throw InvalidCredentialsException(e)
            } else {
                throw e
            }
        }
      return response
    }


}
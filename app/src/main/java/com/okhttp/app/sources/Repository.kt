package com.okhttp.app.sources

import com.okhttp.app.sources.base.DataSource
import com.okhttp.app.sources.base.exeption.BackendException
import com.okhttp.app.sources.base.exeption.InvalidCredentialsException
import com.okhttp.app.sources.entities.Header
import javax.inject.Inject

class Repository@Inject constructor(private val dataSource: DataSource) {


    suspend fun data(key1: Header, key2: Header): String {
        val response = try {
            dataSource.data(key1, key2)
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

}
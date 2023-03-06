package com.okhttp.app.data.network

import android.util.Log
import com.okhttp.app.data.entities.Data
import com.okhttp.app.data.entities.ResponseEntityApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.Request
import javax.inject.Inject

class OkHttpDataSource @Inject constructor(
    config: OkHttpConfig
): BaseOkHttpSource(config), DataSource {





    override suspend fun getData(endpoint: String): List<Data> {
        val request = Request.Builder()
            .endpoint(endpoint)
            .get()
            .addHeader("X-RapidAPI-Key", "ca3478cbf3msh23da0f8b70b504dp1e05f3jsn049c9d93a77e")
            .addHeader("X-RapidAPI-Host", "wft-geo-db.p.rapidapi.com")
            .build()
        val response = client.newCall(request).suspendEnqueue()
        return response.parseJsonResponse<ResponseEntityApi>().data
    }


}
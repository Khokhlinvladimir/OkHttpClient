package com.okhttp.app.sources.base

import com.google.gson.Gson
import com.okhttp.app.sources.base.exeption.BackendException
import com.okhttp.app.sources.base.exeption.ConnectionException
import com.okhttp.app.sources.base.exeption.ParseBackendResponseException
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.*
import java.io.IOException
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

/**
 * Base class for all OkHttp sources.
 */
open class BaseOkHttpSource @Inject constructor(
    private val config: OkHttpConfig
) {
    val gson: Gson = config.gson
    val client: OkHttpClient = config.client


    suspend fun Call.suspendEnqueue(): Response {
        return suspendCancellableCoroutine { continuation ->
            continuation.invokeOnCancellation {
                cancel()
            }
            enqueue(object : Callback {

                override fun onFailure(call: Call, e: IOException) {
                    val appException = ConnectionException(e)
                    continuation.resumeWithException(appException)
                }

                override fun onResponse(call: Call, response: Response) {
                    if (response.isSuccessful) {
                        // well done
                        continuation.resume(response)
                    } else {
                        handleErrorResponse(response, continuation)
                    }
                }
            })
        }
    }

    inline fun <reified T> Response.parseJsonResponse(): T {
        try {
            return gson.fromJson(this.body!!.string(), T::class.java)
        } catch (e: Exception) {
            throw ParseBackendResponseException(e)
        }
    }

    private fun handleErrorResponse(
        response: Response,
        continuation: CancellableContinuation<Response>
    ) {
        val httpCode = response.code
        try {
            val map = gson.fromJson(response.body!!.string(), Map::class.java)
            val message = map["error"].toString()
            continuation.resumeWithException(BackendException(httpCode, message))
        } catch (e: Exception) {
            val appException = ParseBackendResponseException(e)
            continuation.resumeWithException(appException)
        }
    }

}
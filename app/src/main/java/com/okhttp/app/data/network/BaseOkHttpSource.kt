package com.okhttp.app.data.network

import com.google.gson.Gson
import com.okhttp.app.data.Const
import com.okhttp.app.data.network.exeption.BackendException
import com.okhttp.app.data.network.exeption.ConnectionException
import com.okhttp.app.data.network.exeption.ParseBackendResponseException
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import java.io.IOException
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

/**
 * Base class for all OkHttp network.
 */
open class BaseOkHttpSource @Inject constructor(
    private val config: OkHttpConfig
) {
    val gson: Gson = config.gson
    val client: OkHttpClient = config.client

    /**
     * Suspending function which wraps OkHttp [Call.enqueue] method for making
     * HTTP requests and wraps external exceptions into classes of AppException.
     *
     * @throws ConnectionException
     * @throws BackendException
     * @throws ParseBackendResponseException
     */

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

                        continuation.resume(response)


                    } else {
                        handleErrorResponse(response, continuation)
                    }
                }
            })
        }
    }

    /**
     * Parse OkHttp [Response] instance into data object. The type is derived from
     * used to parse JSON arrays.
     *
     * @throws ParseBackendResponseException
     */

    inline fun <reified T> Response.parseJsonResponse(): T {
        try {
            return gson.fromJson(this.body!!.string(), T::class.java)
        } catch (e: Exception) {
            throw ParseBackendResponseException(e)
        }
    }

    /**
     * Concatenate the base URL with a path and query args.
     */
    fun Request.Builder.endpoint(endpoint: String): Request.Builder {
        val urlBuilder = Const.BASE_URL.toHttpUrlOrNull()?.newBuilder()
        urlBuilder?.addQueryParameter("countryIds", endpoint)
        urlBuilder?.addQueryParameter("limit", "10")
        urlBuilder?.addQueryParameter("minPopulation", "800000")
        urlBuilder?.addQueryParameter("maxPopulation", "20000000")
        url(urlBuilder?.build().toString())
        return this
    }

    /**
     * 1. Convert error response from the server into [BackendException] and throw the latter.
     * 2. Throw [ParseBackendResponseException] if error response parsing
     * process has been failed.
     */

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
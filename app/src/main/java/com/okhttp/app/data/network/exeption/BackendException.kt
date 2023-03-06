package com.okhttp.app.data.network.exeption

import javax.inject.Inject

open class BackendException @Inject constructor(
    val code: Int,
    message: String
) : AppException(message)
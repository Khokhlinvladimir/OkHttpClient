package com.okhttp.app.sources.base.exeption

open class BackendException(
    val code: Int,
    message: String
) : AppException(message)
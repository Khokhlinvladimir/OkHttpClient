package com.okhttp.app.sources.base.exeption

class ParseBackendResponseException(
    cause: Throwable
) : AppException(cause = cause)
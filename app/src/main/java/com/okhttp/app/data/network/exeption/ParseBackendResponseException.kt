package com.okhttp.app.data.network.exeption

import javax.inject.Inject

class ParseBackendResponseException @Inject constructor(
    cause: Throwable
) : AppException(cause = cause)
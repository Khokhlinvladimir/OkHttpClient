package com.okhttp.app.sources.base.exeption

import javax.inject.Inject

class ParseBackendResponseException @Inject constructor(
    cause: Throwable
) : AppException(cause = cause)
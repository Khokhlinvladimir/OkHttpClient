package com.okhttp.app.sources.base.exeption

import javax.inject.Inject

class ConnectionException @Inject constructor(cause: Throwable) : AppException(cause = cause)

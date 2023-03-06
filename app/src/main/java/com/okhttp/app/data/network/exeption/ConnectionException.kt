package com.okhttp.app.data.network.exeption

import javax.inject.Inject

class ConnectionException @Inject constructor(cause: Throwable) : AppException(cause = cause)

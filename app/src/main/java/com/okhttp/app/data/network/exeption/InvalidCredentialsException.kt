package com.okhttp.app.data.network.exeption

import javax.inject.Inject

class InvalidCredentialsException @Inject constructor(
    cause: Exception
) : AppException(cause = cause)
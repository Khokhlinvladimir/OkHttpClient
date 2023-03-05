package com.okhttp.app.sources.base.exeption

import javax.inject.Inject

class InvalidCredentialsException @Inject constructor(
    cause: Exception
) : AppException(cause = cause)
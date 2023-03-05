package com.okhttp.app.sources.base.exeption

open class AppException : RuntimeException{
    constructor() : super()
    constructor(message: String) : super(message)
    constructor(cause: Throwable) : super(cause)
}

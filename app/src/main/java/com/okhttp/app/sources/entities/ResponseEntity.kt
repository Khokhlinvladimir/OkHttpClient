package com.okhttp.app.sources.entities

import javax.inject.Inject

data class ResponseEntity @Inject constructor(
    val token: String
)
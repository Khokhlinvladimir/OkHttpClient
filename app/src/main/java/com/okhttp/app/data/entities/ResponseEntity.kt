package com.okhttp.app.data.entities

import javax.inject.Inject

data class ResponseEntity @Inject constructor(
    val token: String
)
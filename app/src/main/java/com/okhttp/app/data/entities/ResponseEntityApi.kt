package com.okhttp.app.data.entities

data class ResponseEntityApi(
    val `data`: List<Data>,
    val links: List<Link>,
    val metadata: Metadata
)
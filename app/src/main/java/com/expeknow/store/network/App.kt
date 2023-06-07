package com.expeknow.store.network

data class App(
    val appGraphic: String? = null,
    val appName: String? = null,
    val complexity: Int? = null,
    val description: String? = null,
    val icon: String? = null,
    val screenshot: List<String>? = null,
    val size: Int? = null,
    val tagLine: String? = null,
    val tags: List<String>? = null
)
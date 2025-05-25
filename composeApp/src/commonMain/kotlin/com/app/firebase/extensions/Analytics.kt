package com.app.firebase.extensions

interface AnalyticsLogger {
    fun logEvent(name: String, params: Map<String, String> = emptyMap())
}
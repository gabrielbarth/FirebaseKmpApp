package com.app.firebase.extensions

interface Analytics {
    fun logEvent(name: String, params: Map<String, Any>? = null)
}
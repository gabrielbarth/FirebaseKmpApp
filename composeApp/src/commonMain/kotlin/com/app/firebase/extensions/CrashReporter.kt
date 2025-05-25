package com.app.firebase.extensions

interface CrashReporter {
    fun log(message: String)
    fun recordException(exception: Throwable)
}
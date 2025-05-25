package com.app.firebase.features.home

import com.app.firebase.extensions.AppServices

class HomeViewModel(
    val userEmail: String
) {
    init {
        AppServices.analytics.logEvent(
            name = "page_viewed",
            mapOf(
                "screen" to "home",
                "user_email" to userEmail
            )
        )
    }

    fun onCrashButtonClicked() {
        AppServices.crashReporter.recordException(
            RuntimeException("Crash for testing Crashlytics from HomeScreen")
        )
        println("HomeViewModel - onCrashButtonClicked - RuntimeException")
        throw RuntimeException("Forced crash from HomeScreen")
    }

    fun onAnalyticsButtonClicked() {
        AppServices.analytics.logEvent(
            name = "home_button_clicked",
            mapOf("screen" to "home")
        )
    }
}

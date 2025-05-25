package com.app.firebase.extensions

import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.logEvent
import android.content.Context

class AndroidAnalyticsLogger(context: Context) : AnalyticsLogger {
    private val firebase = FirebaseAnalytics.getInstance(context)

    override fun logEvent(name: String, params: Map<String, String>) {
        firebase.logEvent(name) {
            params.forEach { (key, value) ->
                when (value) {
                    else -> param(key, value)
                }
            }
        }
    }
}
package com.app.firebase

import AppRoot
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.app.firebase.extensions.AndroidAnalyticsLogger
import com.app.firebase.extensions.AndroidCrashReporter
import com.app.firebase.extensions.AppServices
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)


        FirebaseApp.initializeApp(this)

        AppServices.analytics = AndroidAnalyticsLogger(this)
        AppServices.crashReporter = AndroidCrashReporter()

        setContent {
            AppRoot()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    AppRoot()
}
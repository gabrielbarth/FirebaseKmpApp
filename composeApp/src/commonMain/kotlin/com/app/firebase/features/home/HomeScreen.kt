package com.app.firebase.features.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator

data class HomeScreen(
    val userEmail: String
) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        val viewModel = remember { HomeViewModel(userEmail) }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            ) {
                Text(
                    "Bem-vindo, ${viewModel.userEmail}",
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer(Modifier.height(16.dp))

                Button(
                    onClick = { navigator?.pop() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Sair")
                }

                Button(
                    onClick = { viewModel.onAnalyticsButtonClicked() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Disparar Evento de Analytics")
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { viewModel.onCrashButtonClicked() },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red,
                        contentColor = Color.White
                    )
                ) {
                    Text("Forçar Crash (Crashlytics)")
                }
            }
        }
    }
}

package com.app.firebase.features.login

import com.app.firebase.extensions.AppServices
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel {

    data class UiState(
        val email: String = "",
        val password: String = "",
        val isLoading: Boolean = false,
        val errorMessage: String? = null
    )

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState

    fun onEmailChanged(email: String) {
        _uiState.update { it.copy(email = email, errorMessage = null) }
    }

    fun onPasswordChanged(password: String) {
        _uiState.update { it.copy(password = password, errorMessage = null) }
    }

    init {
        AppServices.analytics.logEvent(
            name = "page_viewed",
            mapOf(
                "screen" to "login",
            )
        )
    }

    suspend fun login(): Boolean {
        _uiState.update { it.copy(isLoading = true, errorMessage = null) }

        println("LoginViewModel - Login Attempt - sending analytics event")
        AppServices.analytics.logEvent(
            name = "login_attempt",
            params = mapOf(
                "screen" to "login",
                "email" to _uiState.value.email,
            )
        )

        return try {
            delay(1000) // Simula chamada de API

            if (_uiState.value.email.isNotBlank() && _uiState.value.password.isNotBlank()) {
                _uiState.update { it.copy(isLoading = false) }

                println("LoginViewModel - Login Success - sending analytics event")
                AppServices.analytics.logEvent(
                    name = "login_success",
                    params = mapOf(
                        "screen" to "login",
                        "email" to _uiState.value.email,
                    )
                )
                true
            } else {
                // just for example purposes
                throw IllegalArgumentException("Email ou senha inválidos")
            }

        } catch (e: Exception) {
            println("LoginViewModel - Login error: $e")
            AppServices.crashReporter.log("Login error")
            AppServices.crashReporter.recordException(e)

            _uiState.update {
                it.copy(isLoading = false, errorMessage = e.message)
            }
            false
        }
    }


}

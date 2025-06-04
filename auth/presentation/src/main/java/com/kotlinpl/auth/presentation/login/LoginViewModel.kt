package com.kotlinpl.auth.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlinpl.auth.domain.AuthError
import com.kotlinpl.auth.domain.AuthService
import com.kotlinpl.auth.domain.UserDataValidator
import com.kotlinpl.auth.presentation.R
import com.kotlinpl.core.domain.util.Result
import com.kotlinpl.core.presentation.ui.UiText
import com.kotlinpl.core.presentation.ui.textAsFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor (
    private val authService: AuthService,
    private val userDataValidator: UserDataValidator
) : ViewModel() {
    var state by mutableStateOf(LoginState())
        private set

    private val eventChannel = Channel<LoginEvent>()
    val events = eventChannel.receiveAsFlow()

    init {
        state.email.textAsFlow()
            .onEach { email ->
                val isEmailValid = userDataValidator.isValidEmail(email.toString())
                state = state.copy(
                    isEmailValid = isEmailValid,
                    canLogin = isEmailValid && !state.password.toString().isEmpty()
                )
            }
            .launchIn(viewModelScope)
    }

    fun onAction(action: LoginActions) {
        when (action) {
            LoginActions.OnLoginClick -> {
                login()
            }
            LoginActions.OnRegisterClick -> Unit
            LoginActions.OnTogglePasswordVisibility -> {
                state = state.copy(isPasswordVisible = !state.isPasswordVisible)
            }
        }
    }

    private fun login() {
        state = state.copy(isLoggingIn = true)
        viewModelScope.launch {
            val result = authService.login(
                email = state.email.text.toString().trim(),
                password = state.password.text.toString()
            )
            state = state.copy(isLoggingIn = false)
            when(result) {
                is Result.Error<AuthError> -> {
                    if(result.error == AuthError.INVALID_CREDENTIALS) {
                        eventChannel.send(LoginEvent.Error(
                            UiText.StringResource(R.string.invalid_credentials)
                        ))
                    }
                }
                is Result.Success -> {
                    eventChannel.send(LoginEvent.LoginSuccess)
                }
            }
        }
    }
}
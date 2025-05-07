package com.kotlinpl.auth.presentation.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlinpl.core.presentation.ui.textAsFlow
import com.kotlinpl.auth.domain.UserDataValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val userDataValidator: UserDataValidator
) : ViewModel() {
    var state by mutableStateOf(RegisterState())
        private set

    init {
        state.email.textAsFlow()
            .onEach { email ->
                val isEmailValid = userDataValidator.isValidEmail(email.toString())
                state = state.copy(
                    isEmailValid = isEmailValid,
                    canRegister = isEmailValid && state.passwordValidationState.isValidPassword
                )
            }
            .launchIn(viewModelScope)

        state.password.textAsFlow()
            .onEach { password ->
                val passwordValidationState = userDataValidator.validatePassword(password.toString())
                state = state.copy(
                    passwordValidationState = passwordValidationState,
                    canRegister = state.isEmailValid && passwordValidationState.isValidPassword
                )
            }
            .launchIn(viewModelScope)
    }

    fun onAction(action: RegisterAction) {
        when(action) {
            RegisterAction.OnLoginClick -> TODO()
            RegisterAction.OnRegisterClick -> register()
            RegisterAction.OnTogglePasswordVisibilityClick -> {
                state.copy(
                    isPasswordVisible = !state.isPasswordVisible
                )
            }
        }
    }

    private fun register() {
        // TODO
    }
}
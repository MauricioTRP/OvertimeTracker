package com.kotlinpl.auth.presentation.register

import androidx.compose.foundation.text.input.TextFieldState
import com.kotlinpl.auth.domain.PasswordValidationState

data class RegisterState(
    val email: TextFieldState = TextFieldState(),
    val isEmailValid: Boolean = false,
    val password: TextFieldState = TextFieldState(),
    val isPasswordVisible: Boolean = false,
    val passwordValidationState: PasswordValidationState = PasswordValidationState(),
    val isRegistering: Boolean = false, //  used to show spinner
    val canRegister: Boolean = passwordValidationState.isValidPassword && !isRegistering
)

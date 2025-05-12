package com.kotlinpl.auth.presentation.login

sealed interface LoginActions {
    data object OnTogglePasswordVisibility : LoginActions
    data object OnLoginClick : LoginActions
    data object OnRegisterClick: LoginActions
}
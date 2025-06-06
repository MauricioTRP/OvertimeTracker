package com.kotlinpl.auth.presentation.login

import com.kotlinpl.core.presentation.ui.UiText

sealed interface LoginEvent {
    data class Error(val error: UiText): LoginEvent
    data object LoginSuccess: LoginEvent
}
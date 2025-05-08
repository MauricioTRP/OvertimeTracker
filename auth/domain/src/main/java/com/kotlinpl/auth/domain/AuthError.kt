package com.kotlinpl.auth.domain

import com.kotlinpl.core.domain.util.Error

enum class AuthError : Error {
    INVALID_CREDENTIALS,
    EMAIL_ALREADY_IN_USE,
    INVALID_EMAIL,
    UNKNOWN
}
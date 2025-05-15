package com.kotlinpl.core.domain.util

import kotlinx.serialization.Serializable

/**
 * Errors handled by app
 */
@Serializable
sealed interface DataError : Error {
    enum class Network : DataError {
        REQUEST_TIMEOUT,
        UNAUTHORIZED,
        CONFLICT,
        TOO_MANY_REQUESTS,
        NO_INTERNET,
        PAYLOAD_TOO_LARGE,
        NOT_FOUND,
        SERVER_ERROR,
        SERIALIZATION,
        UNKNOWN
    }

    @Serializable
    enum class Local : DataError {
        DISK_FULL
    }
}
package com.kotlinpl.core.presentation.ui

import com.kotlinpl.core.domain.util.DataError
import com.kotlinpl.core.presentation.ui.R

typealias StringId = Int

fun DataError.asUiText() : StringId {
    return when (this) {
        DataError.Local.DISK_FULL -> R.string.disk_full
        DataError.Network.REQUEST_TIMEOUT -> R.string.request_timeout
        DataError.Network.UNAUTHORIZED -> R.string.unauthorized
        DataError.Network.CONFLICT -> R.string.conflict
        DataError.Network.TOO_MANY_REQUESTS -> R.string.too_many_requests
        DataError.Network.NO_INTERNET -> R.string.no_internet
        DataError.Network.PAYLOAD_TOO_LARGE -> R.string.payload_too_large
        DataError.Network.SERVER_ERROR -> R.string.server_error
        DataError.Network.SERIALIZATION -> R.string.serialization
        DataError.Network.UNKNOWN -> R.string.unknown_error
    }
}
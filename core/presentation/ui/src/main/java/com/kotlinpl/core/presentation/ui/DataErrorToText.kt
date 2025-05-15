package com.kotlinpl.core.presentation.ui

import com.kotlinpl.core.domain.util.DataError
import com.kotlinpl.core.presentation.ui.R
import com.kotlinpl.core.presentation.ui.UiText

fun DataError.asUiText() : UiText {
    return when (this) {
        DataError.Local.DISK_FULL -> UiText.StringResource(R.string.disk_full)
        DataError.Network.REQUEST_TIMEOUT -> UiText.StringResource(R.string.request_timeout)
        DataError.Network.UNAUTHORIZED -> UiText.StringResource(R.string.unauthorized)
        DataError.Network.CONFLICT -> UiText.StringResource(R.string.conflict)
        DataError.Network.TOO_MANY_REQUESTS -> UiText.StringResource(R.string.too_many_requests)
        DataError.Network.NO_INTERNET -> UiText.StringResource(R.string.no_internet)
        DataError.Network.PAYLOAD_TOO_LARGE -> UiText.StringResource(R.string.payload_too_large)
        DataError.Network.SERVER_ERROR -> UiText.StringResource(R.string.server_error)
        DataError.Network.SERIALIZATION -> UiText.StringResource(R.string.serialization)
        DataError.Network.UNKNOWN -> UiText.StringResource(R.string.unknown_error)
        DataError.Network.NOT_FOUND -> UiText.StringResource(R.string.not_found)
    }
}
package com.kotlinpl.booking.network.activity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("price")
data class PriceDto(
    val amount: Double? = null,
    val currencyCode: String? = null
)

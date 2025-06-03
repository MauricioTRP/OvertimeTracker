package com.kotlinpl.booking.network.activity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ActivityDto(
    val type: String? = null,
    val id: String? = null,
    val name: String? = null,
    val description: String? = null,
    @SerialName("geoCode")
    val geoCodeDto: GeoCodeDto? = null,
    @SerialName("price")
    val price: PriceDto? = null,
    val pictures: List<String> = emptyList(),
    val bookingLink: String? = null,
    val minimumDuration: String? = null
)

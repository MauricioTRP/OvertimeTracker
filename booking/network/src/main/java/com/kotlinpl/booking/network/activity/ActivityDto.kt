package com.kotlinpl.booking.network.activity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ActivityDto(
    val type: String,
    val id: String,
    val name: String,
    val description: String? = null,
    @SerialName("geoCode")
    val geoCodeDto: GeoCodeDto?,
    @SerialName("price")
    val price: PriceDto?,
    val pictures: List<String>,
    val bookingLink: String? = null,
    val minimumDuration: String?
)

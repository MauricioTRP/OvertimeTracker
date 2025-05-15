package com.kotlinpl.booking.network.activity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("geoCode")
data class GeoCodeDto(
    val latitude: Double?,
    val longitude: Double?
)

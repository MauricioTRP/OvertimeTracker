package com.kotlinpl.core.domain.booking

data class GeoCode (
    val latitude: Double?,
    val longitude: Double?
)

fun GeoCode.isNotEmpty() : Boolean {
    return this.latitude != null && this.longitude != null
}
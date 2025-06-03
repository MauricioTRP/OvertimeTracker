package com.kotlinpl.booking.domain.activity

import com.kotlinpl.core.domain.booking.GeoCode

interface OttGeocoderAdapter {
    fun getAddressFromCoordinates(latitude: Double, longitude: Double): String
    fun getCoordinatesFromAddress(address: String): GeoCode
}

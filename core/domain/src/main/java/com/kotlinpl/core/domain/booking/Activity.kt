package com.kotlinpl.core.domain.booking

data class Activity (
    val type: String,
    val id: String,
    val name: String,
    val description: String,
    val geoCode: GeoCode?,
    val price: Price?,
    val pictures: List<String>,
    val bookingLink: String,
    val minimumDuration: String
)

package com.kotlinpl.booking.network.activity

import kotlinx.serialization.Serializable

@Serializable
data class ActivitiesApiResponse(
    val data: List<ActivityDto>
)



package com.kotlinpl.booking.network.activity

import com.kotlinpl.core.domain.util.DataError
import com.kotlinpl.core.domain.util.Result
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ActivitiesApiService {
    @GET("shopping/activities")
    suspend fun getActivities(
        @Header("Authorization") token: String,
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("radius") radius: Int
    ): Response<ActivitiesApiResponse>
}

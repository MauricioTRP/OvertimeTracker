package com.kotlinpl.booking.domain.activity

import com.kotlinpl.core.domain.booking.Activity
import com.kotlinpl.core.domain.util.DataError
import com.kotlinpl.core.domain.util.Result

interface ActivityRepository {
//    suspend fun bookActivity(activity: Activity) : Result<Unit, DataError>
    suspend fun getActivities(latitude: Double, longitude: Double, radius: Int): Result<List<Activity>, DataError>
    suspend fun getActivityById(id: String): Result<Activity?, DataError> {
        return Result.Error(DataError.Network.NOT_FOUND)
    }
//    suspend fun getActivitiesByUserId(userId: String): Result<List<Activity>, DataError>
}

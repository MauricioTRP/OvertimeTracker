package com.kotlinpl.booking.domain.activity

interface ActivityRepository {
    suspend fun bookActivity(activity: Activity)
    suspend fun getActivities(): List<Activity>
    suspend fun getActivityById(id: String): Activity?
    suspend fun getActivitiesByUserId(userId: String): List<Activity>
}

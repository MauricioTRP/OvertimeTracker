package com.kotlinpl.booking.data

import android.util.Log
import com.kotlinpl.booking.domain.activity.ActivityRepository
import com.kotlinpl.booking.network.activity.ActivitiesApiService
import com.kotlinpl.booking.network.activity.ActivityDto
import com.kotlinpl.booking.network.activity.GeoCodeDto
import com.kotlinpl.booking.network.activity.PriceDto
import com.kotlinpl.core.data.Dispatcher
import com.kotlinpl.core.data.OttDispatchers
import com.kotlinpl.core.data.networking.responseToResult
import com.kotlinpl.core.domain.booking.Activity
import com.kotlinpl.core.domain.booking.GeoCode
import com.kotlinpl.core.domain.booking.Price
import com.kotlinpl.core.domain.util.DataError
import com.kotlinpl.core.domain.util.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException
import retrofit2.HttpException
import javax.inject.Inject

class ActivitiesRepositoryImpl @Inject constructor (
    private val activitiesApiService: ActivitiesApiService,
    @Dispatcher(OttDispatchers.IO) private val dispatcher: CoroutineDispatcher
) : ActivityRepository {
//    override suspend fun bookActivity(activity: Activity): Result<Unit, DataError> {
//        TODO("Not yet implemented")
//    }

    override suspend fun getActivities(
        latitude: Double,
        longitude: Double,
        radius: Int
    ): Result<List<Activity>, DataError> {
        val responseActivities =
            activitiesApiService.getActivities(latitude, longitude, radius)
        val responseToResult =
            responseToResult(responseActivities)

        return when (responseToResult) {
            is Result.Success -> {
                val activities = responseToResult.data?.data?.map { it.toActivity() } ?: emptyList()
                Log.d("ActivitiesRepositoryImpl", "Activities: $activities")
                Result.Success(activities)
            }

            is Result.Error<*> -> responseToResult
        }

    }

    override suspend fun getActivityById(id: String): Result<Activity?, DataError> {
        val responseActivity = activitiesApiService.getActivityById(id)

        val responseToResult = responseToResult(responseActivity)

        return when(responseToResult) {
            is Result.Success -> {
                val activity = responseToResult.data?.toActivity()
                Result.Success(activity)
            }
            is Result.Error<*> -> responseToResult
        }
    }
//
//    override suspend fun getActivitiesByUserId(userId: String): Result<List<Activity>, DataError> {
//        TODO("Not yet implemented")
//    }

    private fun ActivityDto.toActivity(): Activity {
        return Activity(
            type = this.type.toString(),
            id = this.id.toString(),
            name = this.name.toString(),
            description = this.description.toString(),
            geoCode = this.geoCodeDto?.toGeocode(),
            price = this.price?.toPrice(),
            pictures = this.pictures,
            bookingLink = this.bookingLink.toString(),
            minimumDuration = this.minimumDuration.toString()
        )
    }

    private fun GeoCodeDto.toGeocode(): GeoCode {
        return GeoCode(
            latitude = this.latitude,
            longitude = this.longitude
        )
    }

    private fun PriceDto.toPrice(): Price {
        return Price(
            amount = this.amount,
            currencyCode = this.currencyCode.toString()
        )
    }
}
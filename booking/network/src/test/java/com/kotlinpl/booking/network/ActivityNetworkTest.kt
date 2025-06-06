package com.kotlinpl.booking.network

import com.kotlinpl.booking.network.activity.ActivitiesApiResponse
import com.kotlinpl.booking.network.activity.ActivitiesApiService
import com.kotlinpl.booking.network.activity.ActivityDto
import com.kotlinpl.booking.network.activity.GeoCodeDto
import com.kotlinpl.booking.network.activity.PriceDto
import com.kotlinpl.core.domain.util.DataError
import com.kotlinpl.core.domain.util.Result
import org.junit.Test
import retrofit2.Response

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

// Mocked data
val activity = ActivityDto(
    type = "activity",
    id = "1",
    name = "Activity 1",
    description = "Description 1",
    geoCodeDto = GeoCodeDto(latitude = 1.0, longitude = 1.0),
    price = PriceDto(amount = 1.0, currencyCode = "CLP"),
    pictures = listOf("picture1", "picture2"),
    bookingLink = "bookingLink",
    minimumDuration = "minimumDuration"
)

class ActivityNetworkTest {


    @Test
    fun activityRepository_getActivities_verifyActivityList() {

    }
}


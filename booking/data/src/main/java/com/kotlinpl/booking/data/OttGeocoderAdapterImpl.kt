package com.kotlinpl.booking.data

import android.location.Geocoder
import com.kotlinpl.booking.domain.activity.OttGeocoderAdapter
import com.kotlinpl.core.data.Dispatcher
import com.kotlinpl.core.data.OttDispatchers
import com.kotlinpl.core.domain.booking.GeoCode
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class OttGeocoderAdapterImpl @Inject constructor (
    private val geocoder: Geocoder,
    @Dispatcher(OttDispatchers.IO) private val dispatcher: CoroutineDispatcher
) : OttGeocoderAdapter {
    override fun getAddressFromCoordinates(latitude: Double, longitude: Double): String {
        return with(CoroutineScope(dispatcher)) {
            try {
                val listAddresses = geocoder.getFromLocation(latitude, longitude, 1)

                listAddresses?.first()?.getAddressLine(0) ?: "Address not found"
            } catch (e: Exception) {
                e.message.toString()
            }
        }
    }

    override fun getCoordinatesFromAddress(address: String): GeoCode {
        return with(CoroutineScope(dispatcher)) {
            try {
                val listAddresses = geocoder.getFromLocationName(address, 1)

                val latitude = listAddresses?.first()?.latitude ?: 0.0
                val longitude = listAddresses?.first()?.longitude ?: 0.0

                GeoCode(latitude, longitude)
            } catch (e: Exception) {
                GeoCode(0.0, 0.0)
            }
        }
    }
}
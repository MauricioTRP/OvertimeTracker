package com.kotlinpl.core.data.networking

import com.kotlinpl.core.domain.util.DataError
import com.kotlinpl.core.domain.util.Result
import kotlinx.coroutines.CancellationException
import kotlinx.serialization.SerializationException
import retrofit2.Response
import java.nio.channels.UnresolvedAddressException


suspend inline fun <reified T> responseToResult(response: Response<T>) : Result<T?, DataError.Network> {
    response.code()
    return when(response.code()) {
        in 200..299 -> Result.Success(response.body())
        401 -> Result.Error(DataError.Network.UNAUTHORIZED)
        408 -> Result.Error(DataError.Network.REQUEST_TIMEOUT)
        409 -> Result.Error(DataError.Network.CONFLICT)
        413 -> Result.Error(DataError.Network.PAYLOAD_TOO_LARGE)
        429 -> Result.Error(DataError.Network.TOO_MANY_REQUESTS)
        in 500..599 -> Result.Error(DataError.Network.SERVER_ERROR)
        else -> Result.Error(DataError.Network.UNKNOWN)
    }
}

suspend inline fun <reified T> safeCall(execute: () -> Response<T>) : Result<T?, DataError.Network> {
    val response = try {
        execute()
    } catch (e: UnresolvedAddressException) {
        e.printStackTrace()
        return Result.Error(DataError.Network.NO_INTERNET)
    } catch (e: SerializationException) {
        e.printStackTrace()
        return Result.Error(DataError.Network.SERIALIZATION)
    } catch (e: Exception) {
        // it propagates cancellation of coroutines and could be catch in here
        if(e is CancellationException) throw e

        e.printStackTrace()
        return Result.Error(DataError.Network.UNKNOWN)
    }

    return responseToResult(response)
}


fun constructRoute(route: String, baseUrl: String) : String {
    if (baseUrl.endsWith("/")) baseUrl.substring(0, baseUrl.length - 1)
    return when {
        route.startsWith("/") -> baseUrl + route
        else -> baseUrl + "/${route}"
    }
}
package com.kotlinpl.core.data.networking

import com.kotlinpl.core.domain.util.DataError
import com.kotlinpl.core.domain.util.Result
//
//import kotlinx.coroutines.CancellationException
//import kotlinx.serialization.SerializationException
//
//suspend inline fun <reified T> responseToResult(response: HttpResponse) : Result<T, DataError.Network> {
//    return when(response.status.value) {
//        in 200..299 -> Result.Success(response.body())
//        401 -> Result.Error(DataError.Network.UNAUTHORIZED)
//        408 -> Result.Error(DataError.Network.REQUEST_TIMEOUT)
//        409 -> Result.Error(DataError.Network.CONFLICT)
//        413 -> Result.Error(DataError.Network.PAYLOAD_TOO_LARGE)
//        429 -> Result.Error(DataError.Network.TOO_MANY_REQUESTS)
//        in 500..599 -> Result.Error(DataError.Network.SERVER_ERROR)
//        else -> Result.Error(DataError.Network.UNKNOWN)
//    }
//}
//
//suspend inline fun <reified T> safeCall(execute: () -> HttpResponse) : Result<T, DataError.Network> {
//    val response = try {
//        execute()
//    } catch (e: UnresolvedAddressException) {
//        e.printStackTrace()
//        return Result.Error(DataError.Network.NO_INTERNET)
//    } catch (e: SerializationException) {
//        e.printStackTrace()
//        return Result.Error(DataError.Network.SERIALIZATION)
//    } catch (e: Exception) {
//        // it propagates cancellation of coroutines and could be catch in here
//        if(e is CancellationException) throw e
//
//        e.printStackTrace()
//        return Result.Error(DataError.Network.UNKNOWN)
//    }
//
//    return responseToResult(response)
//}
//
//suspend inline fun <reified Response: Any> HttpClient.get(
//    route: String,
//    queryParameters: Map<String, Any?> = mapOf(),
//    baseUrl: String = "localhost"
//): Result<Response, DataError> {
//    return safeCall {
//        get {
//            url(constructRoute(route, baseUrl))
//            queryParameters.forEach { (key, value) ->
//                parameter(key, value)
//            }
//        }
//    }
//}
//
//suspend inline fun <reified Request, reified Response: Any> HttpClient.post(
//    route: String,
//    body: Request,
//    baseUrl: String = "localhost"
//): Result<Response, DataError.Network> {
//    return safeCall {
//        post {
//            url(constructRoute(route, baseUrl))
//            setBody(body)
//        }
//    }
//}
//
//suspend inline fun <reified Response: Any> HttpClient.delete(
//    route: String,
//    queryParameters: Map<String, Any?> = mapOf(),
//    baseUrl: String = "localhost"
//): Result<Response, DataError.Network> {
//    return safeCall {
//        delete {
//            url(constructRoute(route, baseUrl))
//            queryParameters.forEach { (key, value) ->
//                parameter(key, value)
//            }
//        }
//    }
//}
//
//fun constructRoute(route: String, baseUrl: String) : String {
//    if (baseUrl.endsWith("/")) baseUrl.substring(0, baseUrl.length - 1)
//    return when {
//        route.startsWith("/") -> baseUrl + route
//        else -> baseUrl + "/${route}"
//    }
//}
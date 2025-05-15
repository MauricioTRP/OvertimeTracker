package com.kotlinpl.core.data.networking

import android.os.SystemClock
import com.kotlinpl.core.data.auth.AccessTokenRequestDto
import com.kotlinpl.core.domain.AuthInfo
import com.kotlinpl.core.domain.SessionStorage
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.logging.HttpLoggingInterceptor
import okio.IOException
import kotlin.math.abs

const val BASE_URL = "https://test.api.amadeus.com/v1/"
const val AUTH_PATH = "security/oauth2/token"

class HttpClientFactory(
    private val sessionStorage: SessionStorage
) {
    fun build() : OkHttpClient {
        val logging = HttpLoggingInterceptor { message ->  }
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                    .header("Content-Type", "application/json")

                val request = requestBuilder.build()
                chain.proceed(request)
            }
            .authenticator { route, response ->
                val info = sessionStorage.get()

                // refresh token

                val newAccessToken = refreshToken(info) ?: return@authenticator null
            }
            .build()
    }

    private fun refreshToken(authInfo: AuthInfo) : String {

        val client = OkHttpClient()
        val (userId, userKey) = Pair(authInfo.userId, authInfo.clientSecret)
        val currentToken = authInfo.accessToken
        val json = Json { ignoreUnknownKeys = true }
        val requestBody = json.encodeToString(AccessTokenRequestDto(userId, userKey))
            .toRequestBody("application/json".toMediaTypeOrNull())

        val request = Request.Builder()
            .url(BASE_URL + AUTH_PATH)
            .post(requestBody)
            .build()

        /**
         * Compares current time with expiration time
         */

        val currentTime = System.currentTimeMillis() / 1000
        val expiresIn = authInfo.expiresIn
        val threeMinutesInSeconds = 60 * 3

        return if (expiresIn - threeMinutesInSeconds < currentTime && currentToken == "") {
            return try {
                val response = client.newCall(request).execute()
                if (response.isSuccessful) {
                    val responseBody = response.body?.string()
                    val accessTokenResponse = json.decodeFromString<AuthInfo>(responseBody!!)
                    accessTokenResponse.accessToken
                } else {
                    ""
                }
            } catch (e: IOException) {
                e.printStackTrace()
                ""
            }
        } else {
            authInfo.accessToken
        }
    }
}

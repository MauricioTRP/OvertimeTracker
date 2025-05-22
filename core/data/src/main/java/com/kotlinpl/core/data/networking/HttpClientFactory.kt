package com.kotlinpl.core.data.networking

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Inject

const val BASE_URL = "https://test.api.amadeus.com/v1/"
const val AUTH_PATH = "security/oauth2/token"

/**
 * Factory class for creating OkHttpClient instances with authentication interceptor.
 *
 * TODO Implement refresh token logic
 */
class HttpClientFactory @Inject constructor (
    private val authenticationInterceptor: AuthenticatorReqInterceptor,
    private val authorizationReqInterceptor: AuthorizationReqInterceptor
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
            .addInterceptor(authorizationReqInterceptor)
            .authenticator(authenticationInterceptor)
            .build()
    }
}

package com.kotlinpl.core.data.networking

import android.util.Log
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.kotlinpl.core.data.auth.AccessTokenRequestDto
import com.kotlinpl.core.data.auth.AccessTokenResponseDto
import com.kotlinpl.core.data.auth.AuthApiService
import com.kotlinpl.core.domain.session.AuthSessionInfo
import com.kotlinpl.core.domain.session.SessionStorage
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import okhttp3.Authenticator
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import retrofit2.Retrofit
import java.io.IOException
import javax.inject.Inject

const val TAG = "AuthenticatorReqInterceptor"

class AuthenticatorReqInterceptor @Inject constructor(
    private val sessionStorage: SessionStorage
) : Authenticator {
    @Throws(IOException::class)
    override fun authenticate(route: Route?, response: Response): Request? {
        val authSessionInfo = runBlocking {
            sessionStorage.getAuthSessionInfo().first()
        }

        Log.d(TAG, "AuthSessionInfo: $authSessionInfo")

        return runBlocking {
            val newToken = refreshToken(authSessionInfo!!)

            Log.d(TAG, "New Token: $newToken")

            if (!newToken.isSuccessful || newToken.body() == null) {
                sessionStorage.setToken("")
            }

            newToken.body()?.let {
                Log.d(TAG, it.accessToken.toString())
                sessionStorage.setToken(it.accessToken.toString())

                response.request.newBuilder()
                    .header("Authorization", "Bearer ${it.accessToken}")
                    .build()
            }
        }
    }

    private suspend fun refreshToken(authSessionInfo: AuthSessionInfo) : retrofit2.Response<AccessTokenResponseDto> {
        val okHttpClient = OkHttpClient.Builder().build()
        val json = Json {
            ignoreUnknownKeys = true
        }

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                json.asConverterFactory(contentType = "application/json".toMediaType())
            )
            .client(okHttpClient)
            .build()

        val service = retrofit.create(AuthApiService::class.java)

        return service.login(
            clientId = authSessionInfo.clientId.toString(),
            clientSecret =  authSessionInfo.clientSecret.toString(),
            grantType = "client_credentials"
        )
    }
}
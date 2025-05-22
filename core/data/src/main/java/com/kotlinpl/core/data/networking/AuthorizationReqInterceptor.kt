package com.kotlinpl.core.data.networking

import com.kotlinpl.core.domain.session.SessionStorage
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

class AuthorizationReqInterceptor @Inject constructor(
    private val sessionStorage: SessionStorage
) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = runBlocking{ sessionStorage.getToken().first() }
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()
        return chain.proceed(request)
    }
}
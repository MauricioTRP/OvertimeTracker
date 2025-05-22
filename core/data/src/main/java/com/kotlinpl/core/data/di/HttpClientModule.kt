package com.kotlinpl.core.data.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.kotlinpl.core.data.networking.AuthenticatorReqInterceptor
import com.kotlinpl.core.data.networking.AuthorizationReqInterceptor
import com.kotlinpl.core.data.networking.HttpClientFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Converter
import javax.inject.Singleton

/**
 * I believe here will have problems
 */
@Module
@InstallIn(SingletonComponent::class)
object HttpClientModule {
    @Provides
    @Singleton
    fun provideHttpClient(
        authorizationReqInterceptor: AuthorizationReqInterceptor,
        authenticatorReqInterceptor: AuthenticatorReqInterceptor
    ): OkHttpClient {
        return HttpClientFactory(authenticatorReqInterceptor, authorizationReqInterceptor).build()
    }
}
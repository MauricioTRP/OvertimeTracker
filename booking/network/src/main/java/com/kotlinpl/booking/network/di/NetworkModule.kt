package com.kotlinpl.booking.network.di

//import com.google.gson.Gson
//import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.kotlinpl.booking.network.activity.ActivitiesApiService
//import com.kotlinpl.core.domain.util.ResultTypeAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val json = Json {
            ignoreUnknownKeys = true
            isLenient = true
        }

        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(contentType = "application/json".toMediaType()))
            .baseUrl("https://test.api.amadeus.com/v1/")
            .build()
    }

    @Provides
    @Singleton
    fun bindActivitiesApiService(retrofit: Retrofit): ActivitiesApiService {
        return retrofit.create(ActivitiesApiService::class.java)
    }
}
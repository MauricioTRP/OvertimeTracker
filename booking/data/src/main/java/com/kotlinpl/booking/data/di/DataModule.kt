package com.kotlinpl.booking.data.di

import android.content.Context
import android.location.Geocoder
import com.kotlinpl.booking.data.ActivitiesRepositoryImpl
import com.kotlinpl.booking.data.OttGeocoderAdapterImpl
import com.kotlinpl.booking.domain.activity.ActivityRepository
import com.kotlinpl.booking.domain.activity.OttGeocoderAdapter
import com.kotlinpl.core.data.Dispatcher
import com.kotlinpl.core.data.OttDispatchers
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule
{
    @Binds
    @Singleton
    abstract fun bindActivitiesRepository(impl: ActivitiesRepositoryImpl): ActivityRepository

    companion object {
        @Provides
        @Singleton
        fun provideGeocoder(
            @ApplicationContext context: Context
        ) = Geocoder(context)

        @Provides
        @Singleton
        fun provideGeocoderAdapter(
            geocoder: Geocoder,
            @Dispatcher(OttDispatchers.IO) dispatcher: CoroutineDispatcher
        ) : OttGeocoderAdapter {
            return OttGeocoderAdapterImpl(geocoder, dispatcher)
        }
    }
}


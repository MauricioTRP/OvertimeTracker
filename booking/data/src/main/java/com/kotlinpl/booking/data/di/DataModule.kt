package com.kotlinpl.booking.data.di

import com.kotlinpl.booking.data.ActivitiesRepositoryImpl
import com.kotlinpl.booking.domain.activity.ActivityRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule
{
    @Binds
    @Singleton
    abstract fun bindActivitiesRepository(impl: ActivitiesRepositoryImpl): ActivityRepository
}


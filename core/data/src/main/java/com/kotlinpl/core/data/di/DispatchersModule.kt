package com.kotlinpl.core.data.di

import com.kotlinpl.core.data.Dispatcher
import com.kotlinpl.core.data.OttDispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule {
    @Provides
    @Dispatcher(OttDispatchers.IO)
    fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Dispatcher(OttDispatchers.DEFAULT)
    fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default
}
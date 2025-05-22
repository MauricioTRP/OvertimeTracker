package com.kotlinpl.core.data.di

import com.kotlinpl.core.data.auth.EncryptedSessionStorage
import com.kotlinpl.core.domain.session.SessionStorage
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class SessionStorageBinding {
    @Binds
    @Singleton
    abstract fun bindsSessionStorage(encryptedSessionStorage: EncryptedSessionStorage): SessionStorage
}
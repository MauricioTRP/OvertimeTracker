package com.kotlinpl.core.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.kotlinpl.core.data.Dispatcher
import com.kotlinpl.core.data.OttDispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

const val AUTH_DATASTORE_NAME = "auth_datastore"

@Module
@InstallIn(SingletonComponent::class)
object AuthDataStoreModule {
    @Provides
    @Singleton
            /**
             * Provides a Preferences DataStore instance for storing authentication-related preferences.
             * @param [ioDispatcher] uses [OttDispatchers] defined and provided in dispatcher modules
             * @param [context] application context provided by @ApplicationContext
             */
    fun providePreferencesDataStore(
        @ApplicationContext context: Context,
        @Dispatcher(OttDispatchers.IO) ioDispatcher: CoroutineDispatcher
    ) : DataStore<Preferences> {
        /**
         * TODO Is needed an implementation of EncryptedFile to encrypt the datastore
         */

        return PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler(
                produceNewData = { emptyPreferences() }
            ),
            scope = CoroutineScope(ioDispatcher + SupervisorJob()),
            produceFile = { context.preferencesDataStoreFile(AUTH_DATASTORE_NAME) },

        )
    }
}
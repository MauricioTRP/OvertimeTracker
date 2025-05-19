package com.kotlinpl.core.data.auth

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kotlinpl.core.domain.session.AuthSessionInfo
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

private const val TEST_DATASTORE_FILE_NAME = "test_datastore"

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class EncryptedSessionStorageTest {
    private val testContext: Context = ApplicationProvider.getApplicationContext()
    private val testScheduler = TestCoroutineScheduler()
    private val testDispatcher = StandardTestDispatcher(testScheduler)
    private val testScope = TestScope(testDispatcher + Job())

    private val testAuthInfoDataStore: DataStore<Preferences> = PreferenceDataStoreFactory.create(
        scope = testScope.backgroundScope,
        produceFile = { testContext.preferencesDataStoreFile(TEST_DATASTORE_FILE_NAME) }
    )

    private val sessionStorage = EncryptedSessionStorage(
        dataStore = testAuthInfoDataStore
    )

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun fetchInitialSessionPreferences() = testScope.runTest {
            /**
             * Setting initial data
             */
            val authSessionInfo = AuthSessionInfo(
                accessToken = "",
                refreshToken = "",
                clientId = "client Id",
                expiresIn = 46543654,
                tokenType = "token type",
                clientSecret = "client secret"
            )

            // "Set initial data"
            sessionStorage.setInitialData(authSessionInfo)

            // "Waiting for data to be set"
            testScheduler.advanceUntilIdle()

            // retrieve stored data to compare with initial data
            val retrievedData = sessionStorage.getAuthSessionInfo()?.first()

            assertEquals(authSessionInfo,retrievedData)
        }

    @Test
    fun set_token_updateAfterInitialConfig() = testScope.runTest {
        /**
         * Define initial Auth State
         */
        val authSessionInfo = AuthSessionInfo(
            accessToken = "old token",
            refreshToken = "",
            clientId = "client Id",
            expiresIn = 46543654,
            tokenType = "Bearer",
            clientSecret = "Secret",
        )

        // "Set initial data"
        sessionStorage.setInitialData(authSessionInfo)

        // "Waiting for data to be set"
        testScheduler.advanceUntilIdle()

        val newTokenConstant = "new token"

        sessionStorage.setToken(newTokenConstant)

        // "Waiting for data to be set"
        testScheduler.advanceUntilIdle()

        val newTokenRetrieved = sessionStorage.getToken().first()

        val newAuthSession = sessionStorage.getAuthSessionInfo().first()

        // "Waiting for data to be set"
        testScheduler.advanceUntilIdle()

        assertEquals(newTokenConstant, newTokenRetrieved)
        assertEquals(newTokenConstant, newAuthSession?.accessToken)
    }

    @After
    fun cleanUp() {
        Dispatchers.resetMain()

        testScope.cancel()
    }
}

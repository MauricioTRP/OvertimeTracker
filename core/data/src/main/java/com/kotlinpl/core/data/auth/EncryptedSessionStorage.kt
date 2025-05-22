package com.kotlinpl.core.data.auth

import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.kotlinpl.core.domain.session.OTT_ACCESS_TOKEN
import com.kotlinpl.core.domain.session.AuthSessionInfo
import com.kotlinpl.core.domain.session.OTT_CLIENT_ID
import com.kotlinpl.core.domain.session.OTT_CLIENT_SECRET
import com.kotlinpl.core.domain.session.OTT_EXPIRES_IN
import com.kotlinpl.core.domain.session.SessionStorage
import com.kotlinpl.core.domain.session.OTT_TOKEN_TYPE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import okhttp3.internal.toLongOrDefault
import javax.inject.Inject

class EncryptedSessionStorage @Inject constructor (
    private val dataStore: DataStore<Preferences>
) : SessionStorage {
    /**
     * AuthDataStore Keys
     */
    private object AuthDataStoreKeys {
        // Amadeus API Key
        val CLIENT_ID_KEY = stringPreferencesKey(OTT_CLIENT_ID)
        // Amadeus API Secret
        val CLIENT_SECRET_KEY = stringPreferencesKey(OTT_CLIENT_SECRET)
        // Amadeus expires in "EPOCH" adaptation
        val EXPIRES_IN_KEY = stringPreferencesKey(OTT_EXPIRES_IN)
        // Amadeus Access Token
        val ACCESS_TOKEN_KEY = stringPreferencesKey(OTT_ACCESS_TOKEN)
    }

    /**
     * Returns the stored [AuthSessionInfo] or null if not found.
     */
    override fun getToken(): Flow<String?> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            mapSessionStorageToAuthInfo(preferences).accessToken
        }

    override suspend fun setToken(token: String) {
        dataStore.edit { preferences ->
            preferences[AuthDataStoreKeys.ACCESS_TOKEN_KEY] = token
        }
    }



    override suspend fun setInitialData(authSessionInfo: AuthSessionInfo) {
        dataStore.edit { preferences ->
            preferences[AuthDataStoreKeys.CLIENT_ID_KEY] = authSessionInfo.clientId.toString()
            preferences[AuthDataStoreKeys.EXPIRES_IN_KEY] = authSessionInfo.expiresIn.toString()
            preferences[AuthDataStoreKeys.ACCESS_TOKEN_KEY] = authSessionInfo.accessToken.toString()
            preferences[AuthDataStoreKeys.CLIENT_SECRET_KEY] = authSessionInfo.clientSecret.toString()
        }
    }

    override suspend fun getAuthSessionInfo(): Flow<AuthSessionInfo?> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        } .map { preferences ->
            mapSessionStorageToAuthInfo(preferences)
        }

    private fun mapSessionStorageToAuthInfo(preferences: Preferences): AuthSessionInfo {
        val clientId = preferences[AuthDataStoreKeys.CLIENT_ID_KEY] ?: ""
        val expiresIn = preferences[AuthDataStoreKeys.EXPIRES_IN_KEY] ?: ""
        val accessToken = preferences[AuthDataStoreKeys.ACCESS_TOKEN_KEY] ?: ""
        val clientSecret = preferences[AuthDataStoreKeys.CLIENT_SECRET_KEY] ?: ""

        /**
         * Amadeus doesn't send refresh token
         * but still useful on other implementations
         */
        return AuthSessionInfo(
            accessToken = accessToken,
            clientId = clientId,
            expiresIn = expiresIn.toLongOrDefault(-1),
            clientSecret = clientSecret
        )
    }
}

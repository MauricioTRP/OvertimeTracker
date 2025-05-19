package com.kotlinpl.core.domain.session

import kotlinx.coroutines.flow.Flow

interface SessionStorage {
    fun getToken() : Flow<String?>
    suspend fun setToken(token: String)
    suspend fun setInitialData(authSessionInfo: AuthSessionInfo)
    suspend fun getAuthSessionInfo() : Flow<AuthSessionInfo?>
}
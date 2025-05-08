package com.kotlinpl.auth.domain

import com.kotlinpl.core.domain.User
import com.kotlinpl.core.domain.util.DataError
import com.kotlinpl.core.domain.util.Result
import kotlinx.coroutines.flow.Flow

interface AuthService {
    val currentUser: Any? // TODO fix type
    val currentUserId: String
    fun hasUser() : Boolean
    suspend fun register(email: String, password: String) : Result<Unit, AuthError>
    suspend fun login(email: String, password: String) : Result<Unit, AuthError>
    suspend fun logout() : Result<Unit, AuthError>
    suspend fun deleteAccount() : Result<Unit, AuthError>
}
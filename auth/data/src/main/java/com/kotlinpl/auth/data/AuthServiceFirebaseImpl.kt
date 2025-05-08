package com.kotlinpl.auth.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.kotlinpl.auth.domain.AuthError
import com.kotlinpl.auth.domain.AuthService
import com.kotlinpl.core.domain.User
import com.kotlinpl.core.domain.util.Result
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthServiceFirebaseImpl @Inject constructor (
    private val firebaseAuth: FirebaseAuth
) : AuthService {
    override val currentUser: FirebaseUser? = firebaseAuth.currentUser

    override val currentUserId: String
        get() = firebaseAuth.currentUser?.uid.orEmpty()

    override fun hasUser(): Boolean {
        return firebaseAuth.currentUser != null
    }

    override suspend fun register(
        email: String,
        password: String
    ): Result<Unit, AuthError> {
        try {
            firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            return Result.Success(Unit)
        } catch (e: FirebaseAuthException) {
            e.printStackTrace()

            return when (e.errorCode) {
                "ERROR_EMAIL_ALREADY_IN_USE" -> Result.Error(AuthError.EMAIL_ALREADY_IN_USE)
                "ERROR_INVALID_EMAIL" -> Result.Error(AuthError.INVALID_EMAIL)
                else -> Result.Error(AuthError.UNKNOWN)
            }
        }
    }

    override suspend fun login(
        email: String,
        password: String
    ): Result<Unit, AuthError> {
        try {
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
            return Result.Success(Unit)
        } catch (e: FirebaseAuthException) {
            e.printStackTrace()

            return when (e.errorCode) {
                "ERROR_INVALID_CREDENTIAL" -> Result.Error(AuthError.INVALID_CREDENTIALS)
                else -> Result.Error(AuthError.UNKNOWN)
            }
        }
    }

    override suspend fun logout(): Result<Unit, AuthError> {
        try {
            firebaseAuth.signOut()
            return Result.Success(Unit)
        } catch (e: FirebaseAuthException) {
            e.printStackTrace()

            return Result.Error(AuthError.UNKNOWN)
        }
    }

    override suspend fun deleteAccount(): Result<Unit, AuthError> {
        try {
            firebaseAuth.currentUser!!.delete().await()
            return Result.Success(Unit)
        } catch (e: FirebaseAuthException) {
            e.printStackTrace()

            return Result.Error(AuthError.UNKNOWN)
        }
    }
}
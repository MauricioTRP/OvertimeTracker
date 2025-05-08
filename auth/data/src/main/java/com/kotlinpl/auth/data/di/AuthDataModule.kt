package com.kotlinpl.auth.data.di

import android.content.Context
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.kotlinpl.auth.data.AuthServiceFirebaseImpl
import com.kotlinpl.auth.data.EmailPatternValidator
import com.kotlinpl.auth.domain.AuthService
import com.kotlinpl.auth.domain.PatternValidator
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthDataModule{
    @Binds
    abstract fun bindPatternValidator(emailPatternValidatorImpl: EmailPatternValidator): PatternValidator

    @Binds
    abstract fun bindAuthService(authServiceImpl: AuthServiceFirebaseImpl): AuthService
}

@Module
@InstallIn(SingletonComponent::class)
object FirebaseAuthModule {
    @Provides
    fun provideFirebaseAuth(@ApplicationContext context: Context) : FirebaseAuth {
        if (FirebaseApp.getApps(context).isEmpty()) {
            FirebaseApp.initializeApp(context)
        }

        return FirebaseAuth.getInstance()
    }
}
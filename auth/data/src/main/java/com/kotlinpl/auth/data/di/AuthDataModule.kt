package com.kotlinpl.auth.data.di

import com.kotlinpl.auth.data.EmailPatternValidator
import com.kotlinpl.auth.domain.PatternValidator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthDataModule{
    @Binds
    abstract fun bindPatternValidator(impl: EmailPatternValidator): PatternValidator
}
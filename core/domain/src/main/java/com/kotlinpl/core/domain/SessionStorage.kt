package com.kotlinpl.core.domain

interface SessionStorage {
    fun get() : AuthInfo?
    fun set(authInfo: AuthInfo)
}
package com.kotlinpl.core.domain

/**
 * User data class represents a user in the application.
 *
 * [id] String - User ID
 * [email] String - User email address]
 */
data class User(
    val id: String,
    val email: String? = null
)

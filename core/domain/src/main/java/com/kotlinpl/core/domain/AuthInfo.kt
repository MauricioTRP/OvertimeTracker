package com.kotlinpl.core.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthInfo(
    @SerialName("access_token")
    val accessToken: String,
    @SerialName("refresh_token")
    val refreshToken: String,
    @SerialName("client_id")
    val userId: String,
    @SerialName("expires_in")
    val expiresIn: Long,
    @SerialName("token_type")
    val tokenType: String,
    @SerialName("client_secret")
    val clientSecret: String
)

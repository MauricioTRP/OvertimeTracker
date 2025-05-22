package com.kotlinpl.core.domain.session

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthSessionInfo(
    @SerialName("access_token")
    val accessToken: String?,
    @SerialName("client_id")
    val clientId: String?,
    @SerialName("expires_in")
    val expiresIn: Long?,
    @SerialName("client_secret")
    val clientSecret: String?
)
